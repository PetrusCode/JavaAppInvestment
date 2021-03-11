package com.netmind.presentation;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;

import com.opencsv.exceptions.CsvException;

public class MainJavaApp {

	public static void main(String[] args)
			throws IOException, CsvException, ParseException {
		Instant start = Instant.now();

		// Menu.investorsOptions();
		Menu.calcularGanancias();
		// InvestorsDao.readCsv();

		Instant finis = Instant.now();
		long time = Duration.between(start, finis).toMillis();
		System.out.println("Tiempo ejecucion de programa\n" + time + " - "
				+ "Milisegundos");
	}

}
