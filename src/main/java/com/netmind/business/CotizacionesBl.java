package com.netmind.business;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import com.netmind.dao.CotizacionesDao;
import com.netmind.model.Cotizaciones;

public class CotizacionesBl {

	@SuppressWarnings("static-access")
	public boolean addCotizaciones(Cotizaciones cotizaciones) throws IllegalStateException, FileNotFoundException {

		CotizacionesDao cotizacionesDao = new CotizacionesDao();

		cotizacionesDao.GetCotizaciones();
		return true;
	}

	public List<LocalDate> getAllLastThursday(LocalDate startDate, LocalDate endDate)
			throws ParseException, IllegalStateException, FileNotFoundException {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		startDate = startDate.withDayOfMonth(1);
		endDate = endDate.withDayOfMonth(1);
		do {
			// Ordenamos la lista de fechas de forma descendente al igual que en
			// el archivo. para reducir el tiempo de busqueda de esta forma ---
			// dates.add(0,
			// lastThursdayInMonth(startDate));
			dates.add(0, lastThursdayInMonth(startDate));
			startDate = startDate.plusMonths(1);
		} while (!startDate.equals(endDate));
		return dates;
	}

	public List<Cotizaciones> getNextDayQuotations(List<Cotizaciones> cotizacionesList)
			throws ParseException, IllegalStateException, FileNotFoundException {
		// Obtenemos todas las cotizaciones

		Cotizaciones firstCot = cotizacionesList.get(0); // Primera cotizacion
															// (ultima en fecha)
		Cotizaciones lastCot = cotizacionesList.get(cotizacionesList.size() - 1); // Ultima cotizacion (Primera
																					// en fecha)

		List<LocalDate> lastThursdaysList = getAllLastThursday(lastCot.getFecha(), firstCot.getFecha());

		// Creamos una lista para ser llenada con dias cotizables
		List<Cotizaciones> nextDayQuotations = new ArrayList<Cotizaciones>();

		for (LocalDate thursday : lastThursdaysList) {
			Cotizaciones nextStock = findNextDayWithStock(thursday, cotizacionesList, 7);
			// Si se encuentra una cotizacion en los proximos 7 dias se agrega a
			// la lista.
			if (nextStock != null)
				nextDayQuotations.add(nextStock);
		}
		return nextDayQuotations;
	}

	/// Busca el siguiente dia con cotizacion hasta un limite de dias siguientes
	public Cotizaciones findNextDayWithStock(LocalDate fecha, List<Cotizaciones> cotizaciones, int limit) {

		// a�adimos un dia la fecha
		LocalDate nextDay = fecha.plusDays(1);

		// Buscamos la cotizacion del dia
		Cotizaciones stock = findStockByDate(nextDay, cotizaciones);

		// Si no se encuentra la cotizacion y no se ha llegado al limite de
		// busqueda,
		// la funcion se llama asi misma para seguir buscando y reduce el
		// limite en un intento.
		if (stock == null && limit > 0) {
			return findNextDayWithStock(nextDay, cotizaciones, limit - 1);
		}
		return stock;
	}

	/// Busca en un listado de cotizaciones una cotizacion segun la fecha.
	public Cotizaciones findStockByDate(LocalDate fecha, List<Cotizaciones> cotizaciones) {
		Cotizaciones stock = null;
		for (Cotizaciones cotizacion : cotizaciones) {

			if (cotizacion.getFecha().equals(fecha)) {
				stock = cotizacion;
				break;
			}
		}
		return stock;
	}

	public LocalDate lastThursdayInMonth(LocalDate date) {

		return date.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));

		// busca los ultimos jueves del mes

	}

	public static void getAllLastThursday() {

	}
}
// Segunda forma
/*
 * public static List<LocalDate> getAllLastThursday2(LocalDate startDate,
 * LocalDate endDate) throws ParseException {
 * 
 * List<LocalDate> dates = new ArrayList<LocalDate>();
 * 
 * long months = getNumOfMonths(startDate, endDate); long count = 0; do {
 * dates.add(lastThursdayInMonth(startDate.plusMonths(count))); count++; } while
 * (count < months); return dates; }
 */
