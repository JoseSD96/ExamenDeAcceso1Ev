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

public class DAOCoche {

	private static Connection crearConexion() throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/cochesdb", "root", "");
		return conexion;
	}

	public List<POJOCoche> leerCoches(boolean vueltas, DAOVuelta gestorVueltas) {
		List<POJOCoche> listaCoches = new ArrayList<POJOCoche>();

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion.prepareStatement("SELECT * FROM coches")) {

			ResultSet resultado = consultaPreparada.executeQuery();

			while (resultado.next()) {
				String marca = resultado.getString("Marca");
				String modelo = resultado.getString("Modelo");
				if (vueltas) {
					List<POJOVuelta> listaVueltas = gestorVueltas.leerVueltasPorCoche(resultado.getInt("id"));
					listaCoches.add(new POJOCoche(marca, modelo, listaVueltas));
				} else {
					listaCoches.add(new POJOCoche(marca, modelo));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCoches;
	}

	public int buscarIDCoche(POJOCoche c) {

		int id = -1;

		try (Connection conexion = crearConexion();
				PreparedStatement consultaPreparada = conexion
						.prepareStatement("SELECT * FROM coches WHERE Marca = ? AND Modelo = ?")) {

			consultaPreparada.setString(1, c.getMarca());
			consultaPreparada.setString(2, c.getModelo());

			ResultSet resultado = consultaPreparada.executeQuery();

			while (resultado.next()) {
				id = resultado.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

}
