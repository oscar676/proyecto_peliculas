package com.ipartek.formacion.oscarfonticoba.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Ignore;

import com.ipartek.formacion.oscarfonticoba.pojo.Pelicula;

public class PeliculaDAOTest {

	static DbConnection db;
	static Connection conn;
	static PeliculaDAO dao;
	static Pelicula pMock1, pMock2;
	static int id1, id2; // identificador de la última operación realizada en el
							// DAO

	@Ignore
	public static void setUpBeforeClass() throws Exception {

		db = new DbConnection();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		dao = new PeliculaDAO();
	}

	@Ignore
	public static void tearDownAfterClass() throws Exception {
		conn.rollback();
		db.desconectar();
		db = null;
		dao = null;
	}

	@Ignore
	public void setUp() throws Exception {

		pMock1 = new Pelicula("Mock", "Accion", 120);
		pMock2 = new Pelicula("Mock2", "Drama", 140);

		id1 = dao.insert(pMock1);
		assertTrue("No se ha realizado la inserción", id1 > 0);
	}

	@Ignore
	public void tearDown() {
		try {
			assertTrue("No se pudo eliminar", dao.delete(id1));
		} catch (SQLException e) {
			fail("Hay algún problema en el test: " + e.getMessage());
		}
	}

	@Ignore
	public void testGetAll() {
		try {
			int peliculasSize = dao.getAll().size();
			assertTrue("debería al menos recuperar una persona",
					peliculasSize > 0);
			id2 = dao.insert(pMock2);
			assertTrue("No se ha realizado la inserción", id2 > 0);
			// assertTrue("debería al menos recuperar dos peliculas",
			// (peliculasSize + 1) == dao.getAll().size());//No funciona dado
			// que getAll recupera solo 500 y hay m�s de 500
			assertTrue("No se pudo eliminar", dao.delete(id2));
		} catch (SQLException e) {
			fail("Hay algún problema en el test: " + e.getMessage());
		}
	}

	@Ignore
	public void testGetById() {
		Pelicula p1;
		try {
			p1 = dao.getById(id1);
			System.out.println(pMock1 + "\n" + p1);
			assertTrue("No tienen los mismos atributos", pMock1.equals(p1));
		} catch (SQLException e) {
			fail("Hay algún problema en el test: " + e.getMessage());
		}
	}

	// Comprobar caso de id inexistente
	@Ignore
	public void testDelete() {
		try {
			assertFalse("No puede eliminar lo que no existe", dao.delete(-1));
		} catch (SQLException e) {
			fail("Hay algún problema en el test: " + e.getMessage());
		}
	}

	@Ignore
	public void testUpdate() {
		try {
			Pelicula p2 = dao.getById(id1);
			p2.setTitulo("Batman");
			p2.setGenero("Policial");
			p2.setDuracion(240);
			assertTrue(dao.update(p2));
			assertTrue("No tienen los mismos atributos",
					p2.equals(dao.getById(id1)));

			// Test null
			assertFalse(
					"no se puede modifica la base una peliculas que no existe",
					dao.update(null));

			// Test persona vac�a
			Pelicula p3 = new Pelicula();
			assertFalse(
					"no se puede modifica la base una persona que no existe",
					dao.update(p3));

		} catch (SQLException e) {
			fail("Hay algún problema en el test: " + e.getMessage());
		}

		// Test persona que no existe
	}

	@Ignore
	public void testInsert() {
		// assertTrue("No ha dado error al insertar una pelicula null",
		// dao.insert(null) == -1);
	}
}