package com.ipartek.formacion.oscarfonticoba.pojo;

/**
 * Pojo para crear objetos de la clase Pelicula
 *
 * @author Oscar
 *
 */
public class Pelicula {

	private int id, duracion;
	private String titulo, genero;

	/**
	 * Constructor vacio de la clase Pelicula
	 */
	public Pelicula() {
		super();
		this.id = -1;
		this.titulo = "";
		this.genero = "";
		this.duracion = 0;

	}

	/**
	 * Constructor al que le pasamos los siguientes parametros:
	 *
	 * @param titulo
	 * @param genero
	 * @param duracion
	 */

	public Pelicula(String titulo, String genero, int duracion) {
		this();
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;

	}

	/**
	 * Getter para el atributo id. Nos permite recuperar el id
	 *
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setter del atributo id. Nos permite modificar el atributo id
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter del atributo duracion. Nos permite recuperar dicho atributo
	 *
	 * @return duracion
	 */

	public int getDuracion() {
		return this.duracion;
	}

	/**
	 * Setter del atributo duracion. Nos permite modificar su valor
	 *
	 * @param duracion
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * Getter del atributo titulo. Nos permite recuperar su valor
	 *
	 * @return titulo
	 */

	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * Setter del atributo titulo. Nos permite modificar su valor
	 *
	 * @param titulo
	 */

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Getter del atributo genero. Nos permite recuperar su valor
	 *
	 * @return genero
	 */
	public String getGenero() {
		return this.genero;
	}

	/**
	 * Setter del atributo genero. Nos permite modificar su valor
	 *
	 * @param genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.duracion;
		result = (prime * result)
				+ ((this.genero == null) ? 0 : this.genero.hashCode());
		result = (prime * result) + this.id;
		result = (prime * result)
				+ ((this.titulo == null) ? 0 : this.titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pelicula other = (Pelicula) obj;
		if (this.duracion != other.duracion) {
			return false;
		}
		if (this.genero == null) {
			if (other.genero != null) {
				return false;
			}
		} else if (!this.genero.equals(other.genero)) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (this.titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!this.titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pelicula [this.id=" + this.id + ", this.duracion="
				+ this.duracion + ", this.titulo=" + this.titulo
				+ ", this.genero=" + this.genero + "]";
	}

}