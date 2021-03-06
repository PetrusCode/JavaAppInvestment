package com.netmind.dao;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.netmind.model.Investors;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class InvestorsDao {

	private static List<Investors> arrayList = null;

	static {
		arrayList = new ArrayList<Investors>();
	}

	public boolean add(Investors investors) {

		return arrayList.add(investors);
	}

	public static List<Investors> readCsv() throws IOException, CsvException, ParseException {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

		try (CSVReader reader = new CSVReaderBuilder(new FileReader("stocks-ITX.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			List<String[]> r2 = reader.readAll();
			r2.forEach(x -> System.out.println(Arrays.toString(x)));
		}

		return arrayList;

	}

	public static int getDayNumberOld(String sDate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = formatter.parse(sDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.THURSDAY);
	}
}
