package com.ipartek.formacion.oscarfonticoba.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.oscarfonticoba.pojo.Pelicula;

/**
 * DAO para realizar las operaciones de CRUD contra la base de datos peliculas
 * 
 * @author Oscar
 *
 */
public class PeliculaDAO implements Persistable<Pelicula> {
	private final static Logger log = Logger.getLogger(PeliculaDAO.class);

	@Override
	public List<Pelicula> getAll() throws SQLException {
		// Se abre conexión
		DbConnection conn = new DbConnection();

		// Crear consulta
		String sql = "select `id`,`titulo`,`genero`,`duracion` from peliculas";

		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);

		// ejecutar la consulta
		ResultSet res = consulta.executeQuery();

		// ArrayList de personas
		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		// iterar sobre los resultados
		while (res.next()) {
			lista.add(mapeo(res));
		}
		res.close();
		consulta.close();
		conn.desconectar();
		return lista;
	}

	@Override
	public Pelicula getById(int id) throws SQLException {
		// Se abre conexión
		DbConnection conn = new DbConnection();
		// nombre de la clase y ctrl + espacio
		String sql = "select `id`,`titulo`,`genero`,`duracion` from peliculas where `id` = ?;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setInt(1, id);

		// ejecutar la consulta
		ResultSet res = consulta.executeQuery();

		// iterar sobre los resultados
		res.next();
		Pelicula p = mapeo(res);

		res.close();
		consulta.close();
		conn.desconectar();
		return p;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		DbConnection conn = new DbConnection();
		boolean resul = false;
		String sql = "delete from `peliculas` where `id` = ?;";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		if (pst.executeUpdate() == 1) {
			resul = true;
		}
		return resul;
	}

	@Override
	public boolean update(Pelicula persistable) throws SQLException {
		boolean resul = false;
		if (persistable != null) {
			DbConnection conn = new DbConnection();
			String sql = "update `peliculas` set titulo = ?, genero = ?, duracion = ? where `id` = ? ;";
			PreparedStatement pst = conn.getConnection().prepareStatement(sql);
			pst.setString(1, persistable.getTitulo());
			pst.setString(2, persistable.getGenero());
			pst.setInt(3, persistable.getDuracion());
			pst.setInt(4, persistable.getId());
			if (pst.executeUpdate() == 1) {
				resul = true;
			}
		}
		return resul;
	}

	@Override
	public int insert(Pelicula persistable) {
		int i = -1;
		if (persistable != null) {
			// Se abre conexión
			DbConnection conn = new DbConnection();
			try {
				String sql = "INSERT INTO `peliculas` (`titulo`, `genero`, `duracion`) VALUES (?,?,?);";
				PreparedStatement pst = conn.getConnection().prepareStatement(
						sql, PreparedStatement.RETURN_GENERATED_KEYS);

				pst.setString(1, persistable.getTitulo());
				pst.setString(2, persistable.getGenero());
				pst.setInt(3, persistable.getDuracion());
				// ejecutar la consulta. Si no afecta a una línea, lanzamos la
				// excepción
				if (pst.executeUpdate() != 1) {
					throw new SQLException("No se ha insertado el dato");
				}

				ResultSet generatedKeys = pst.getGeneratedKeys();
				generatedKeys.next();
				i = generatedKeys.getInt(1);
				persistable.setId(i);
				pst.close();

			} catch (Exception e) {
				i = -1;
				e.printStackTrace();
				log.error("Excepcion al insertar en la base de datos "
						+ e.getMessage());
			}
			conn.desconectar();
		}
		return i;
	}

	/**
	 * Mapeo entre los atributos del pojo de pelicula y lo que devuelve la sql
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 */

	private Pelicula mapeo(ResultSet res) throws SQLException {

		Pelicula peli = new Pelicula();
		peli.setId(res.getInt("id"));
		peli.setTitulo(res.getString("titulo"));
		peli.setGenero(res.getString("genero"));
		peli.setDuracion(res.getInt("duracion"));

		return peli;
	}

}