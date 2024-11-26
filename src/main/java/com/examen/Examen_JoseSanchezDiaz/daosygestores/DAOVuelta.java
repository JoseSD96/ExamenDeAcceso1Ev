package com.examen.Examen_JoseSanchezDiaz.daosygestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examen.Examen_JoseSanchezDiaz.pojos.POJOCoche;
import com.examen.Examen_JoseSanchezDiaz.pojos.POJOVuelta;

public class DAOVuelta {

	private static Connection crearConexion() throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/cochesdb", "root", "");
		return conexion;
	}

	public List<POJOVuelta> leerVueltasPorCoche(int idCcohe) {
		List<POJOVuelta> listaVueltas = new ArrayList<POJOVuelta>();

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
						.prepareStatement("SELECT * FROM circuitos WHERE id_coche = ?")) {

			consultaPreparada.setInt(1, idCcohe);

			ResultSet resultado = consultaPreparada.executeQuery();

			while (resultado.next()) {
				String circuito = resultado.getString("Nombre");
				int tiempo = resultado.getInt("Tiempo");

				listaVueltas.add(new POJOVuelta(circuito, tiempo));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaVueltas;

	}

	public boolean addVueltaACoche(DAOCoche gestorCoche, POJOCoche c, POJOVuelta v) {
		int idCoche = gestorCoche.buscarIDCoche(c);

		if (idCoche == -1) {
			return false;
		}

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
						.prepareStatement("INSERT INTO circuitos (id_coche, Nombre, Tiempo) VALUES (?,?,?)")) {

			consultaPreparada.setInt(1, idCoche);
			consultaPreparada.setString(2, v.getCircuito());
			consultaPreparada.setInt(3, v.getTiempo());
			
			consultaPreparada.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean eliminarVueltasACoche(DAOCoche gestorCoche, POJOCoche c) {
		int idCoche = gestorCoche.buscarIDCoche(c);

		if (idCoche == -1) {
			return false;
		}

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
						.prepareStatement("DELETE FROM circuitos WHERE id_coche = ?")) {

			consultaPreparada.setInt(1, idCoche);
			
			consultaPreparada.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
