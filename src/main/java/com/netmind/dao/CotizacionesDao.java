package com.netmind.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.netmind.model.Cotizaciones;
import com.opencsv.bean.CsvToBeanBuilder;

public class CotizacionesDao {

	public static void GetCotizaciones()
			throws IllegalStateException, FileNotFoundException {
		String fileName = "stocks-ITX.csv";
		List<Cotizaciones> beans = new CsvToBeanBuilder(
				new FileReader(fileName)).withType(Cotizaciones.class)
						.withSkipLines(1).withSeparator(';').build().parse();

		int i = 0;
		for (Cotizaciones cot : beans) {
			try {
				if (getDayNumber(cot.getFecha()) == 5) {
					i++;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(i);
		System.out.println(beans.size());

	}

	public static int getDayNumber(String sDate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = formatter.parse(sDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}
