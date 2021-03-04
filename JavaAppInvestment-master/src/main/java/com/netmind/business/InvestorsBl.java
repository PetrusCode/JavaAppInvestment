package com.netmind.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.netmind.dao.InvestorsDao;
import com.netmind.model.Investors;
import com.opencsv.CSVParser;

public class InvestorsBl {
	Investors investors = new Investors();
	InvestorsDao investorsDao = new InvestorsDao();

	public boolean add(Investors investors) {
		InvestorsDao investorsDao = new InvestorsDao();

		return investorsDao.add(investors);
	}

	public static BigDecimal calculateStock(Date fecha) {
		InvestorsDao investorsDao = new InvestorsDao();

		return null;

	}

	public static List<LocalDate> roadM() {
		CSVParser csvParser = new CSVParser();

		return null;

	}

	public static int getDayNumberOld(String sDate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = formatter.parse(sDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}
