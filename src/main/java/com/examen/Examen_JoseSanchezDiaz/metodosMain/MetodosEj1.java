package com.examen.Examen_JoseSanchezDiaz.metodosMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import com.examen.Examen_JoseSanchezDiaz.daosygestores.GestorXML;
import com.examen.Examen_JoseSanchezDiaz.pojos.POJOCoche;
import com.examen.Examen_JoseSanchezDiaz.pojos.POJOVuelta;

public class MetodosEj1 {
	public static int mostrarMenuEj1(Scanner sc) {
		int opcion = -1;
		do {
			System.out.println("""
					-----MENU-----
					1) Añadir coche al XML
					2) Ver el XML
					3) Pasar XML a JSON
					0) Salir del ejercicio
					""");
			try {
				opcion = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("Por favor indique un numero");
				sc.nextLine();
			}
			if (opcion < 0 || opcion > 3) {
				System.err.println("Indique una opcion disponible");
			}
		} while (opcion < 0 || opcion > 3);
		return opcion;
	}

	public static void addCocheXML(Scanner sc, GestorXML gestor) {
		System.out.println("Indica la marca del coche: ");
		String marca = sc.nextLine();

		System.out.println("Indica el modelo del coche: ");
		String modelo = sc.nextLine();

		System.out.println("¿Cuantas vueltas rapidas tiene el coche? (Por defecto 1)");
		int vueltasRapidas = sc.nextInt();
		sc.nextLine();
		if (vueltasRapidas < 1) {
			vueltasRapidas = 1;
		}
		List<POJOVuelta> vueltas = new ArrayList<POJOVuelta>();
		for (int i = 0; i < vueltasRapidas; i++) {
			System.out.println("Indica el circuito");
			String circuito = sc.nextLine();

			System.out.println("Indica el tiempo: ");
			int tiempo = sc.nextInt();
			sc.nextLine();
			vueltas.add(new POJOVuelta(circuito, tiempo));
		}

		POJOCoche coche = new POJOCoche(marca, modelo, vueltas);

		gestor.crearXML(coche);
	}

	public static void leerXML(GestorXML gestor) {
		List<POJOCoche> coches = gestor.leerXML();

		if (coches == null) {
			System.out.println("La lista esta vacia");
		} else {
			for (POJOCoche c : coches) {
				System.out.println(c);
			}
		}
	}

	public static void crearJSON(GestorXML gestor) {
		JSONObject json = gestor.pasarXMLaJSON();

		String txtJSON = json.toString(4);

		System.out.println(txtJSON);
	}
}
