package com.examen.Examen_JoseSanchezDiaz.pojos;

import java.util.List;

public class POJOCoche {

	int id;

	String marca;
	String modelo;
	List<POJOVuelta> vueltas;

	public POJOCoche(int id, String marca, String modelo, List<POJOVuelta> vueltas) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.vueltas = vueltas;
	}

	public POJOCoche(String marca, String modelo, List<POJOVuelta> vueltas) {
		this.marca = marca;
		this.modelo = modelo;
		this.vueltas = vueltas;
	}

	public POJOCoche(String marca, String modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}

	public POJOCoche() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<POJOVuelta> getVueltas() {
		return vueltas;
	}

	public void setVueltas(List<POJOVuelta> vueltas) {
		this.vueltas = vueltas;
	}

	@Override
	public String toString() {
		return "Coche [marca=" + marca + ", modelo=" + modelo + ", vueltas=" + vueltas + "]";
	}

	public String toStringSinVueltas() {
		return "Coche [marca=" + marca + ", modelo=" + modelo + "]";
	}
}
