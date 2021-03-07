package com.netmind.model;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;

public class Cotizaciones {

	@CsvDate("dd-MMM-yyyy")
	@CsvBindByPosition(position = 0)
	private LocalDate fecha;

	@CsvNumber("000,0")
	@CsvBindByPosition(position = 1)
	private Double cierre;

	@CsvNumber("000,0")
	@CsvBindByPosition(position = 2)
	private Double apertura;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Double getCierre() {
		return cierre;
	}

	public void setCierre(Double cierre) {
		this.cierre = cierre;
	}

	public Double getApertura() {
		return apertura;
	}

	public void setApertura(Double apertura) {
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
