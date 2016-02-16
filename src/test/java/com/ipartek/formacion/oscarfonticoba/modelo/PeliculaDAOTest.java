package com.ipartek.formacion.oscarfonticoba.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.ipartek.formacion.oscarfonticoba.pojo.Pelicula;

public class PeliculaDAOTest {

	static DbConnection db;
	static Connection conn;
	static PeliculaDAO dao;
	static Pelicula pMock1, pMock2;
	static int id; // identificador de la última operación realizada en el DAO

	@Test
	public void setUpBeforeClass() throws Exception {

		db = new DbConnection();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		dao = new PeliculaDAO();
	}

	@Test
	public void tearDownAfterClass() throws Exception {
		conn.rollback();
		db.desconectar();
		db = null;
		dao = null;
	}

	@Test
	public void test() {
		try {

			pMock1 = new Pelicula("Mock1", "Accion", 120);
			pMock2 = new Pelicula("Mock2", "Drama", 140);

			// Comprobar que el tamaño de la base de datos es mayor de 0

			int peliculasSize = dao.getAll().size();
			assertTrue("Debería al menos recuperar una persona",
					peliculasSize > 0);

			// Comprobar si realiza una inserción

			id = dao.insert(pMock1);
			assertTrue("No se ha realizado la inserción", id > 0);

			// Comprobar que recupera por id

			Pelicula p1 = new Pelicula();

			p1 = dao.getById(id);

			assertTrue("No tienen los mismos atributos", pMock1.equals(p1));

			// Comprobar caso de id inexistente

			assertFalse("No puede eliminar lo que no existe", dao.delete(-1));

			// Comprobar que actualiza la base de datos

			Pelicula p2 = dao.getById(id);
			p2.setTitulo("Batman");
			p2.setGenero("Policial");
			p2.setDuracion(240);

			assertTrue(dao.update(p2));
			assertTrue("No tienen los mismos atributos",
					p2.equals(dao.getById(id)));

			// Comprobar que no actualiza si le pasamos un null

			assertFalse(
					"no se puede modifica la base una peliculas que no existe",
					dao.update(null));

			// Comprobar que no actualiza una película que no existe

			Pelicula p3 = new Pelicula();
			assertFalse(
					"no se puede modifica la base una persona que no existe",
					dao.update(p3));

			// Comprobar que no inserta una pelicula null

			assertTrue("No ha dado error al insertar una pelicula null",
					dao.insert(null) == -1);

			// Comprobar que realiza la eliminación

			assertTrue("No se pudo eliminar", dao.delete(id));

		} catch (SQLException e) {
			fail("Hay algún problema en el test: " + e.getMessage());
		}
	}
}