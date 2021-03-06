package com.netmind.model;

import com.opencsv.bean.CsvBindByPosition;

public class Cotizaciones {

	@CsvBindByPosition(position = 0)
	private String fecha;

	@CsvBindByPosition(position = 1)
	private String cierre;

	@CsvBindByPosition(position = 2)
	private String apertura;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCierre() {
		return cierre;
	}

	public void setCierre(String cierre) {
		this.cierre = cierre;
	}

	public String getApertura() {
		return apertura;
	}

	public void setApertura(String apertura) {
		this.apertura = apertura;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cotizaciones [fecha=");
		builder.append(fecha);
		builder.append(", cierre=");
		builder.append(cierre);
		builder.append(", apertura=");
		builder.append(apertura);
		builder.append("]");
		return builder.toString();
	}
	
	
	// getters, setters, toString
}
