package com.netmind.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.netmind.dao.InvestorsDao;
import com.netmind.model.Investors;

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

	/*
	 * public static void lastThursday(int month, int year) { LocalDate
	 * lastThursday = LocalDate.of(year, month, 1).with(lastInMonth(THURSDAY));
	 * System.out.println("lastThursday = " + lastThursday); }
	 */

	public static int getDayNumberOld(String sDate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = formatter.parse(sDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}
