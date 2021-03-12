package com.netmind.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.netmind.model.Cotizaciones;
import com.opencsv.bean.CsvToBeanBuilder;

public class CotizacionesDao {

	public static List<Cotizaciones> GetCotizaciones()
			throws IllegalStateException, FileNotFoundException {
		String fileName = "stocks-ITX.csv";
		List<Cotizaciones> beans = new CsvToBeanBuilder<Cotizaciones>(
				new FileReader(fileName)).withType(Cotizaciones.class)
						.withSkipLines(1).withSeparator(';').build().parse();
		return beans;
	}

	// segunda forma
	/*
	 * public static long getNumOfMonths(LocalDate startDate, LocalDate endDate)
	 * { return ChronoUnit.MONTHS.between(startDate, endDate); }
	 */
}
