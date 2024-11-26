package com.examen.Examen_JoseSanchezDiaz;

import java.util.Scanner;

import com.examen.Examen_JoseSanchezDiaz.daosygestores.DAOCoche;
import com.examen.Examen_JoseSanchezDiaz.daosygestores.DAOVuelta;
import com.examen.Examen_JoseSanchezDiaz.daosygestores.GestorXML;
import com.examen.Examen_JoseSanchezDiaz.metodosMain.MetodosEj1;
import com.examen.Examen_JoseSanchezDiaz.metodosMain.MetodosEj2;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GestorXML gestor = new GestorXML();
		DAOCoche gestorCoche = new DAOCoche();
		DAOVuelta gestorVueltas = new DAOVuelta();

		boolean salir = false;
		boolean salirEjercicio = false;

		do {
			switch (menuEjercicios(sc)) {

			case 1:
				salirEjercicio = false;
				do {
					switch (MetodosEj1.mostrarMenuEj1(sc)) {
					case 1:
						MetodosEj1.addCocheXML(sc, gestor);
						break;
					case 2:
						MetodosEj1.leerXML(gestor);
						break;
					case 3:
						MetodosEj1.crearJSON(gestor);
						break;
					case 0:
						salirEjercicio = true;
						break;
					}
				} while (!salirEjercicio);
				break;

			case 2:
				salirEjercicio = false;
				do {
					switch (MetodosEj2.mostrarMenuEj2(sc)) {
					case 1:
						MetodosEj2.listarCochesBBDD(gestorCoche, gestorVueltas, false);
						break;
					case 2:
						MetodosEj2.listarCochesBBDD(gestorCoche, gestorVueltas, true);
						break;
					case 3:
						MetodosEj2.addVueltaRapidaACoche(gestorCoche, gestorVueltas, sc);
						break;
					case 4:
						MetodosEj2.eliminarVueltasACoche(gestorCoche, gestorVueltas, sc);
						break;
					case 0:
						salirEjercicio = true;
						break;
					}
				} while (!salirEjercicio);
				break;
			case 0:
				System.out.println("Cerrando el programa . . .");
				salir = true;
			}
		} while (!salir);
	}

	public static int menuEjercicios(Scanner sc) {
		int opcion = -1;
		do {
			System.out.println("""
					-----MENU-----
					1) Ejercicio 1
					2) Ejercicio 2
					0) Salir
					""");
			try {
				opcion = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.err.println("Por favor indique un numero");
				sc.nextLine();
			}
			if (opcion < 0 || opcion > 2) {
				System.err.println("Indique 1 o 2");
			}
		} while (opcion < 0 || opcion > 2);
		return opcion;
	}

}
