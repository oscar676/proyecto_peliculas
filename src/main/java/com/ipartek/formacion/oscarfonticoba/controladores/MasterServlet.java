package com.ipartek.formacion.oscarfonticoba.controladores;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.oscarfonticoba.modelo.PeliculaDAO;

/**
 * Servlet implementation class MasterServlet
 *
 * @author oscar
 */
public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// DAOs
	protected static PeliculaDAO daoPelicula;

	/**
	 * Se encarga de enrutar Solo puede ir a un sitio, no se puede cargar dos
	 * veces
	 *
	 */
	protected RequestDispatcher dispatch;

	/**
	 * Fichero de properties
	 */
	protected ResourceBundle messages;
	/**
	 * Mensaje a mostrar la usuario
	 */
	protected static Mensaje msj;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoPelicula = new PeliculaDAO();

	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		daoPelicula = null;

	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		msj = null;
		this.messages = null;

		super.service(request, response);
	}

}