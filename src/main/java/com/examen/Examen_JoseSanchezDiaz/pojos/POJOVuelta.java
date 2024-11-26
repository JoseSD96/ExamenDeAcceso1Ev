package com.examen.Examen_JoseSanchezDiaz.pojos;

public class POJOVuelta {
	int id;
	String circuito;
	int tiempo;

	public POJOVuelta(int id, String circuito, int tiempo) {
		this.id = id;
		this.circuito = circuito;
		this.tiempo = tiempo;
	}

	public POJOVuelta(String circuito, int tiempo) {
		this.circuito = circuito;
		this.tiempo = tiempo;
	}
	
	public POJOVuelta() {
		
	}

	public String getCircuito() {
		return circuito;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "Vuelta [circuito=" + circuito + ", tiempo=" + tiempo + "]";
	}

}
