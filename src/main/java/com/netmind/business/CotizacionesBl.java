package com.netmind.business;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.netmind.dao.CotizacionesDao;
import com.netmind.dao.InvestorsDao;
import com.netmind.model.Cotizaciones;

public class CotizacionesBl {

	@SuppressWarnings("static-access")
	public static boolean addCotizaciones(Cotizaciones cotizaciones)
			throws IllegalStateException, FileNotFoundException {

		CotizacionesDao cotizacionesDao = new CotizacionesDao();

		cotizacionesDao.GetCotizaciones(cotizaciones);
		return true;
	}

	public static List<LocalDate> getAllLastThursday(LocalDate startDate,
			LocalDate endDate) throws ParseException {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		startDate = startDate.withDayOfMonth(1);
		endDate = endDate.withDayOfMonth(1);
		do {
			dates.add(lastThursdayInMonth(startDate));
			startDate = startDate.plusMonths(1);
		} while (!startDate.equals(endDate));
		return dates;
	}

	public static LocalDate lastThursdayInMonth(LocalDate date) {
		return date.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
	}

	public static void getAllLastThursday() {

	}

	public static BigDecimal calculateCotizacion(Date fecha)
			throws IllegalStateException, FileNotFoundException {
		Cotizaciones cotizaciones = new Cotizaciones();
		CotizacionesDao cotizacionesDao = new CotizacionesDao();
		InvestorsDao investorsDao = new InvestorsDao();
		List<Cotizaciones> cotis = CotizacionesDao
				.GetCotizaciones(cotizaciones);

		Cotizaciones firts = cotis.get(0);
		Cotizaciones lastCot = cotis.get(cotis.size() - 1);
		return null;

	}
	// Segunda forma
	/*
	 * public static List<LocalDate> getAllLastThursday2(LocalDate startDate,
	 * LocalDate endDate) throws ParseException {
	 * 
	 * List<LocalDate> dates = new ArrayList<LocalDate>();
	 * 
	 * long months = getNumOfMonths(startDate, endDate); long count = 0; do {
	 * dates.add(lastThursdayInMonth(startDate.plusMonths(count))); count++; }
	 * while (count < months); return dates; }
	 */
}
