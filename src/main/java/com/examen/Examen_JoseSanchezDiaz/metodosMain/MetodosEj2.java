package com.examen.Examen_JoseSanchezDiaz.metodosMain;

import java.util.List;
import java.util.Scanner;

import com.examen.Examen_JoseSanchezDiaz.daosygestores.DAOCoche;
import com.examen.Examen_JoseSanchezDiaz.daosygestores.DAOVuelta;
import com.examen.Examen_JoseSanchezDiaz.pojos.POJOCoche;
import com.examen.Examen_JoseSanchezDiaz.pojos.POJOVuelta;

public class MetodosEj2 {

	public static int mostrarMenuEj2(Scanner sc) {
		int opcion = -1;
		do {
			System.out.println("""
					-----MENU-----
					1) Mostrar coches
					2) Mostrar coches y vueltas
					3) Añadir vuelta rapida a un coche existente
					4) Eliminar vueltas rapidas de un coche
					0) Salir del ejercicio
					""");

			try {
				opcion = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("Por favor indique un numero");
				sc.nextLine();
			}
			if (opcion < 0 || opcion > 4) {
				System.err.println("Indique una opcion disponible");
			}
		} while (opcion < 0 || opcion > 4);
		return opcion;
	}

	public static void listarCochesBBDD(DAOCoche gestorCoche, DAOVuelta gestorVueltas, boolean vueltas) {
		List<POJOCoche> coches = gestorCoche.leerCoches(vueltas, gestorVueltas);

		for (POJOCoche c : coches) {
			if (vueltas) {
				System.out.println(c);
			} else {
				System.out.println(c.toStringSinVueltas());
			}

		}
	}

	public static void addVueltaRapidaACoche(DAOCoche gestorCoches, DAOVuelta gestorVueltas, Scanner sc) {

		System.out.println("Indique la marca del coche al que quiere añadir la vuelta: ");
		String marcaBuscada = sc.nextLine();

		System.out.println("Indique el modelo del coche al que quiere añadir la vuelta: ");
		String modeloBuscado = sc.nextLine();

		POJOCoche buscado = new POJOCoche(marcaBuscada, modeloBuscado);

		if (gestorCoches.buscarIDCoche(buscado) == -1) {
			System.out.println("No se encontro el coche");
		} else {
			System.out.println("Indique el circuito donde se hizo la vuelta: ");
			String circuito = sc.nextLine();

			System.out.println("Indica el tiempo que hizo: ");
			int tiempo = sc.nextInt();
			sc.nextLine();

			POJOVuelta nueavaVuelta = new POJOVuelta(circuito, tiempo);

			if (gestorVueltas.addVueltaACoche(gestorCoches, buscado, nueavaVuelta)) {
				System.out.println("Vuelta añadida");
			} else {
				System.out.println("No se pudo añadir la vuelta");
			}
		}
	}

	public static void eliminarVueltasACoche(DAOCoche gestorCoches, DAOVuelta gestorVueltas, Scanner sc) {
		System.out.println("Indique la marca del coche al que quiere añadir la vuelta: ");
		String marcaBuscada = sc.nextLine();

		System.out.println("Indique el modelo del coche al que quiere añadir la vuelta: ");
		String modeloBuscado = sc.nextLine();

		POJOCoche buscado = new POJOCoche(marcaBuscada, modeloBuscado);

		if (gestorCoches.buscarIDCoche(buscado) == -1) {
			System.out.println("No se encontro el coche");
		} else {
			if (gestorVueltas.eliminarVueltasACoche(gestorCoches, buscado)) {
				System.out.println("Vueltas eliminadas correctamente");
			} else {
				System.out.println("No se pudieron eliminar");
			}
		}
	}

}
