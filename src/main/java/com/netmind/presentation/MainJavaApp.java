package com.netmind.presentation;

import java.io.IOException;
import java.text.ParseException;

import com.netmind.dao.CotizacionesDao;
import com.netmind.dao.InvestorsDao;
import com.opencsv.exceptions.CsvException;

public class MainJavaApp {

	public static void main(String[] args)
			throws IOException, CsvException, ParseException {
		Menu.investorsOptions();
		InvestorsDao.readCsv();
		CotizacionesDao.GetCotizaciones();
		System.out.println(System.getProperty("java.version"));
	}

}
