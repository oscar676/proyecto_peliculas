package com.ipartek.formacion.oscarfonticoba.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.oscarfonticoba.Constantes;
import com.ipartek.formacion.oscarfonticoba.pojo.Pelicula;

/**
 * Servlet implementation class UsuarioServlet
 */
public class PeliculaServlet extends MasterServlet {

	private static final long serialVersionUID = 1L;

	private static int operacion;
	private static String pId; // Parámetro identificador del usuario, aunque

	// sea un id, es un string, luego se parsea

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
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
			// TODO mejor en un LOG
			e.printStackTrace();

			// TODO ir a página error 404.jsp o 500.jsp
		}
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
		int pId = Integer.parseInt(request.getParameter("id")), pDuracion = Integer
				.parseInt(request.getParameter("duracion"));
		String pTitulo = request.getParameter("titulo"), pGenero = request
				.getParameter("genero");

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
			} else {
				msj = new Mensaje("No se ha insertado la pelicula",
						Mensaje.TIPO_WARNING);
			}
		} else if (daoPelicula.update(peli)) {
			msj = new Mensaje("Pelicula modificada con éxito",
					Mensaje.TIPO_SUCCESS);
		} else {
			msj = new Mensaje("No se ha modificado el registro",
					Mensaje.TIPO_WARNING);
		}
		// listar
		listar(request);
	}

	private void eliminar(HttpServletRequest request) throws SQLException {
		try {
			int pId = Integer.parseInt(request.getParameter("id"));
			if (daoPelicula.delete(pId)) {
				msj = new Mensaje("Registro eliminado con éxito",
						Mensaje.TIPO_SUCCESS);
			} else {
				msj = new Mensaje("No se ha eliminado el registro",
						Mensaje.TIPO_DANGER);
			}
		} catch (Exception e) {
			msj = new Mensaje("No se ha eliminado el registro",
					Mensaje.TIPO_DANGER);
		}
		listar(request);
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
	}

	private void listar(HttpServletRequest request) throws SQLException {

		// Llamar modelo para obtener listado
		ArrayList<Pelicula> listaPeliculas = (ArrayList<Pelicula>) daoPelicula
				.getAll();

		// Guardar listado como atributo en request
		request.setAttribute("listaPeliculas", listaPeliculas);

		// Petición interna a la jsp (RequestDistapecher es para decirle a donde
		// tiene que ir, se carga el dispatcher)
		dispatch = request.getRequestDispatcher(Constantes.VIEW_PELICULA_LIST);

	}

	private void detalle(HttpServletRequest request)
			throws NumberFormatException, SQLException {
		pId = request.getParameter("id");
		Pelicula peli = daoPelicula.getById(Integer.parseInt(pId));
		request.setAttribute("pelicula", peli);

		dispatch = request.getRequestDispatcher(Constantes.VIEW_PELICULA_FORM);
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

}