package com.examen.Examen_JoseSanchezDiaz.utiles;

import java.io.File;
import java.io.IOException;

public class Utiles {

	public static File getDirectorioPadre(String nombre) {
		File carpeta = new File(nombre);

		if (!carpeta.exists()) {
			carpeta.mkdir();
		}

		return carpeta;

	}

	public static File crearFichero(String nombre, String extension) {
		String carpeta = getDirectorioPadre("ficheros").getName();
		File archivo = new File(carpeta + "\\" + nombre + "." + extension);

		if (!archivo.exists()) {
			try {
				archivo.createNewFile();
				System.out.println("Creado el archivo: " + nombre + "." + extension);
			} catch (IOException e) {
				System.err.println("Error al crear el archivo: " + nombre + "." + extension);
				e.printStackTrace();
				return null;
			}
		}
		return archivo;
	}
}
