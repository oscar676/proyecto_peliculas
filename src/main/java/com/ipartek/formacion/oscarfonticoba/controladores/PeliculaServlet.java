﻿package com.ipartek.formacion.oscarfonticoba.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.oscarfonticoba.Constantes;
import com.ipartek.formacion.oscarfonticoba.pojo.Pelicula;

/**
 * Servlet implementation class UsuarioServlet
 */
public class PeliculaServlet extends MasterServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(PeliculaServlet.class);

	private static int operacion;
	private static String pId; // Parámetro identificador del usuario, aunque

	// sea un id, es un string, luego se parsea

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log.trace("Init servlet PeliculaServlet");
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
		log.trace("service servlet PeliculaServlet");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			// recoger parámetros a realizar
			if (request.getParameter("op") != null) {
				operacion = Integer.parseInt(request.getParameter("op"));
			} else {
				operacion = Constantes.OP_LISTAR;
			}

			// Realizar accion
			switch (operacion) {
			case Constantes.OP_LISTAR:
				listar(request);
				break;
			case Constantes.OP_DETALLE:
				detalle(request);
				break;
			case Constantes.OP_NUEVO:
				nuevo(request);
				break;
			case Constantes.OP_ELIMINAR:
				eliminar(request);
				break;
			case Constantes.OP_MODIFICAR:
				modificarCrear(request);
				break;
			}
			request.setAttribute("msj", msj);
			/*
			 * forward para servir la jsp (lanzarlo). en forward hay que poner
			 * dos argumentos. doGet tiene dos request y response, al ser una
			 * petición interna, hay que poner los mismos
			 */
			dispatch.forward(request, response);
		} catch (Exception e) {
			log.error("Excepcion al recibir el GET " + e.getMessage());

		}
		log.trace("DoGet servlet PeliculaServlet");
	}

	/**
	 * Modifica o crea una nueva pelicula
	 *
	 * @param request
	 * @throws ParseException
	 * @throws SQLException
	 */
	private void modificarCrear(HttpServletRequest request)
			throws ParseException, SQLException {

		// recoger parámetros formulario
		int pId = Integer.parseInt(request.getParameter("id"));
		String pTitulo = request.getParameter("titulo"), pGenero = request
				.getParameter("genero");
		int pDuracion = 0;
		if (isNumeric(request.getParameter("duracion"))) {
			pDuracion = Integer.parseInt(request.getParameter("duracion"));

		} else {
			pDuracion = 0;
		}

		// construir pelicula
		Pelicula peli = new Pelicula();
		peli.setId(pId);
		peli.setTitulo(pTitulo);
		peli.setGenero(pGenero);
		peli.setDuracion(pDuracion);

		// persistir en la bbdd
		if (peli.getId() == -1) {
			if (daoPelicula.insert(peli) != -1) {
				msj = new Mensaje("Pelicula insertado con éxito",
						Mensaje.TIPO_SUCCESS);
				log.info("Pelicula insertada con éxito");
			} else {
				msj = new Mensaje("No se ha insertado la pelicula",
						Mensaje.TIPO_WARNING);
				log.info("No se ha insertado la pelicula");
			}
		} else if (daoPelicula.update(peli)) {
			msj = new Mensaje("Pelicula modificada con éxito",
					Mensaje.TIPO_SUCCESS);
			log.info("Pelicula modificada con éxito");
		} else {
			msj = new Mensaje("No se ha modificado el registro",
					Mensaje.TIPO_WARNING);
			log.info("No se ha modificado el registro");
		}
		// llama al metodo listar pasandole la request
		listar(request);
		log.trace("modificarCrear servlet PeliculaServlet");
	}

	/**
	 * Elimina una pelicula de la base de datos
	 *
	 * @param request
	 * @throws SQLException
	 */
	private void eliminar(HttpServletRequest request) throws SQLException {
		try {
			int pId = Integer.parseInt(request.getParameter("id"));
			if (daoPelicula.delete(pId)) {
				msj = new Mensaje("Registro eliminado con éxito",
						Mensaje.TIPO_SUCCESS);
				log.info("Registro eliminado con éxito");
			} else {
				msj = new Mensaje("No se ha eliminado el registro",
						Mensaje.TIPO_DANGER);
				log.info("No se ha eliminado el registro");
			}
		} catch (Exception e) {
			msj = new Mensaje("No se ha eliminado el registro",
					Mensaje.TIPO_DANGER);
			log.error("Excepcion al eliminar " + e.getMessage());
		}
		listar(request);
		log.trace("eliminar Servlet PeliculaServlet");
	}

	/**
	 * Nos lleva a la vista del formulario para crear una pelicula
	 *
	 * @param request
	 * @throws SQLException
	 */
	private void nuevo(HttpServletRequest request) throws SQLException {
		Pelicula pPeli = new Pelicula();
		request.setAttribute("pelicula", pPeli);

		dispatch = request.getRequestDispatcher(Constantes.VIEW_PELICULA_FORM);
		log.trace("nuevo servlet PeliculaServlet");
	}

	/**
	 * Lista las peliculas de la base de datos
	 *
	 * @param request
	 * @throws SQLException
	 */
	private void listar(HttpServletRequest request) throws SQLException {

		// Llamar modelo para obtener listado
		ArrayList<Pelicula> listaPeliculas = (ArrayList<Pelicula>) daoPelicula
				.getAll();

		// Guardar listado como atributo en request
		request.setAttribute("listaPeliculas", listaPeliculas);

		// Petición interna a la jsp (RequestDistapecher es para decirle a donde
		// tiene que ir, se carga el dispatcher)
		dispatch = request.getRequestDispatcher(Constantes.VIEW_PELICULA_LIST);
		log.trace("listar servlet PeliculaServlet");
	}

	/**
	 * Muestra el detalle de una pelicula para modificar sus datos
	 *
	 * @param request
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private void detalle(HttpServletRequest request)
			throws NumberFormatException, SQLException {
		pId = request.getParameter("id");
		Pelicula peli = daoPelicula.getById(Integer.parseInt(pId));
		request.setAttribute("pelicula", peli);

		dispatch = request.getRequestDispatcher(Constantes.VIEW_PELICULA_FORM);
		log.trace("detalle servlet PeliculaServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Comprueba si un String es un número o no
	 *
	 * @param cadena
	 * @return
	 */
	private static boolean isNumeric(String cadena) {
		boolean resul = false;
		try {
			Integer.parseInt(cadena);
			resul = true;
		} catch (NumberFormatException nfe) {
			log.error("Dato introducido incorrecto ");
		}
		return resul;
	}

}