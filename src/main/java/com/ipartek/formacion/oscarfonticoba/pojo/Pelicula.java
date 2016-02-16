package com.ipartek.formacion.oscarfonticoba.pojo;

public class Pelicula {

	private int id, duracion;
	private String titulo, genero;

	public Pelicula() {
		super();
		id = -1;
		this.titulo = "";
		this.genero = "";
		this.duracion = 0;

	}

	public Pelicula(String titulo, String genero, int duracion) {
		this();
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + duracion;
		result = (prime * result) + ((genero == null) ? 0 : genero.hashCode());
		result = (prime * result) + id;
		result = (prime * result) + ((titulo == null) ? 0 : titulo.hashCode());
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
		if (duracion != other.duracion) {
			return false;
		}
		if (genero == null) {
			if (other.genero != null) {
				return false;
			}
		} else if (!genero.equals(other.genero)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", duracion=" + duracion + ", titulo="
				+ titulo + ", genero=" + genero + "]";
	}

}