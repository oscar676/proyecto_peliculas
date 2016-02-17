package com.ipartek.formacion.oscarfonticoba.controladores;

/**
 * Clase que configura los mensajes que se muestran al usuario
 * 
 * @author Oscar
 *
 */
public class Mensaje {

	// Constantes para tipos
	public static final String TIPO_SUCCESS = "success";
	public static final String TIPO_INFO = "info";
	public static final String TIPO_WARNING = "warning";
	public static final String TIPO_DANGER = "danger";

	private String texto;
	private String tipo;

	/**
	 * Constructor vacio para la clase Mensaje
	 *
	 */
	public Mensaje() {
		super();
		this.texto = "Error desconocido";
		this.tipo = TIPO_DANGER;
	}

	/**
	 * Constructor para la clase Mensaje al que se le pasan dos parametros
	 *
	 * @param texto
	 * @param tipo
	 */
	public Mensaje(String texto, String tipo) {
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	/**
	 * Getter para recuperar el texto del mensaje
	 *
	 * @return texto
	 */
	public String getTexto() {
		return this.texto;
	}

	/**
	 * Setter para modificar el contenido del texto
	 *
	 * @param texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Getter para recuperar el tipo del texto del mensaje
	 *
	 * @return tipo
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Setter para modificar el tipo del mensaje
	 *
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
