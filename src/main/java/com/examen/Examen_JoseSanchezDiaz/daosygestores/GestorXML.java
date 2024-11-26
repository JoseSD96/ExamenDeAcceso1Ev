package com.examen.Examen_JoseSanchezDiaz.daosygestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.examen.Examen_JoseSanchezDiaz.pojos.POJOCoche;
import com.examen.Examen_JoseSanchezDiaz.pojos.POJOVuelta;
import com.examen.Examen_JoseSanchezDiaz.utiles.Utiles;

public class GestorXML {

	public DocumentBuilder crearBuilder() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("Erorr al crear el builder");
			e.printStackTrace();
		}

		return builder;

	}

	public Document crearDocumentoXML() {
		DocumentBuilder builder = crearBuilder();

		DOMImplementation implementacion = builder.getDOMImplementation();

		Document documento = implementacion.createDocument(null, "coches", null);
		documento.setXmlVersion("1.0");

		return documento;
	}

	public static Element crearElementoSinTexto(String nombre, Element padre, Document documento) {
		Element elemento = documento.createElement(nombre);
		padre.appendChild(elemento);

		return elemento;
	}

	public static void crearElementoConTexto(String nombre, String valor, Element padre, Document documento) {
		Element elemento = documento.createElement(nombre);
		Text texto = documento.createTextNode(valor);

		elemento.appendChild(texto);
		padre.appendChild(elemento);
	}

	public Document leerFicheroXML(File f) {
		DocumentBuilder builder = crearBuilder();

		Document documento = null;
		try {
			documento = builder.parse(f);
		} catch (SAXException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}

		return documento;
	}

	public boolean crearXML(POJOCoche c) {
		File archivo = Utiles.crearFichero("coches", "xml");

		if (archivo == null) {
			return false;
		}

		Document documento = null;

		if (archivo.length() == 0) {
			documento = crearDocumentoXML();
		} else {
			documento = leerFicheroXML(archivo);
		}

		Element coche = crearElementoSinTexto("coche", documento.getDocumentElement(), documento);

		crearElementoConTexto("marca", c.getMarca(), coche, documento);
		crearElementoConTexto("modelo", c.getModelo(), coche, documento);

		Element vueltas = crearElementoSinTexto("vueltas", coche, documento);

		for (POJOVuelta v : c.getVueltas()) {
			Element vuelta = crearElementoSinTexto("vuelta", vueltas, documento);
			crearElementoConTexto("circuito", v.getCircuito(), vuelta, documento);
			crearElementoConTexto("tiempo", Integer.toString(v.getTiempo()), vuelta, documento);
		}

		Source origen = new DOMSource(documento);
		Result resultado = new StreamResult(archivo);

		try {
			Transformer transformador = TransformerFactory.newInstance().newTransformer();
			transformador.transform(origen, resultado);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			return false;
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public List<POJOCoche> leerXML() {
		List<POJOCoche> listaCoches = new ArrayList<POJOCoche>();
		File archivo = Utiles.crearFichero("coches", "xml");

		if (archivo == null) {
			return listaCoches;
		}

		Document documento = leerFicheroXML(archivo);
		NodeList coches = documento.getElementsByTagName("coche");

		for (int i = 0; i < coches.getLength(); i++) {
			List<POJOVuelta> listaVueltas = new ArrayList<POJOVuelta>();
			Element coche = (Element) coches.item(i);

			String marca = coche.getElementsByTagName("marca").item(0).getTextContent();
			String modelo = coche.getElementsByTagName("modelo").item(0).getTextContent();

			NodeList vueltas = coche.getElementsByTagName("vuelta");

			for (int j = 0; j < vueltas.getLength(); j++) {
				Element vuelta = (Element) vueltas.item(j);

				String circuito = vuelta.getElementsByTagName("circuito").item(0).getTextContent();
				String txtTiempo = vuelta.getElementsByTagName("tiempo").item(0).getTextContent();
				int tiempo = Integer.parseInt(txtTiempo);

				listaVueltas.add(new POJOVuelta(circuito, tiempo));
			}

			listaCoches.add(new POJOCoche(marca, modelo, listaVueltas));
		}

		return listaCoches;
	}

	public JSONObject pasarXMLaJSON() {
		File xml = Utiles.crearFichero("coches", "xml");
		File json = Utiles.crearFichero("coches", "json");
		JSONObject txtJSON = null;
		String txtXML = "";

		try (FileReader fr = new FileReader(xml); BufferedReader br = new BufferedReader(fr)) {
			txtXML = br.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			txtJSON = XML.toJSONObject(txtXML);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return txtJSON;
		
	}
}
