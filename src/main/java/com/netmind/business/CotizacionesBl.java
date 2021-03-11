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
	public boolean addCotizaciones(Cotizaciones cotizaciones)
			throws IllegalStateException, FileNotFoundException {

		CotizacionesDao cotizacionesDao = new CotizacionesDao();

		cotizacionesDao.GetCotizaciones();
		return true;
	}

	public List<LocalDate> getAllLastThursday(LocalDate startDate,
			LocalDate endDate) throws ParseException, IllegalStateException,
			FileNotFoundException {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		startDate = startDate.withDayOfMonth(1);
		endDate = endDate.withDayOfMonth(1).plusMonths(1);
		do {
			// Ordenamos la lista de fechas de forma descendente al igual que en
			// el archivo. para reducir el tiempo de busqueda de esta forma ---
			// dates.add(0,
			// lastThursdayInMonth(startDate));
			dates.add(lastThursdayInMonth(startDate));
			startDate = startDate.plusMonths(1);
		} while (!startDate.equals(endDate));
		return dates;
	}

	public List<Cotizaciones> getNextDayQuotations() throws ParseException,
			IllegalStateException, FileNotFoundException {
		// Obtenemos todas las cotizaciones
		List<Cotizaciones> cotizacionesList = CotizacionesDao.GetCotizaciones();
		Cotizaciones firstCot = cotizacionesList.get(0); // Primera cotización
															// (ultima en fecha)
		Cotizaciones lastCot = cotizacionesList
				.get(cotizacionesList.size() - 1); // Ultima cotización (Primera
													// en fecha)

		List<LocalDate> lastThursdaysList = getAllLastThursday(
				lastCot.getFecha(), firstCot.getFecha());

		// Creamos una lista para ser llenada con días cotizables
		List<Cotizaciones> nextDayQuotations = new ArrayList<Cotizaciones>();

		for (LocalDate thursday : lastThursdaysList) {
			Cotizaciones nextStock = findNextDayWithStock(thursday,
					cotizacionesList, 7);
			// Si se encuentra una cotización en los próximos 7 días se agrega a
			// la lista.
			if (nextStock != null)
				nextDayQuotations.add(nextStock);
		}
		return nextDayQuotations;
	}

	/// Busca el siguiente día con cotización hasta un límite de días siguientes
	public Cotizaciones findNextDayWithStock(LocalDate fecha,
			List<Cotizaciones> cotizaciones, int limit) {

		// Añadimos un día a la fecha
		LocalDate nextDay = fecha.plusDays(1);

		// Buscamos la cotización del día
		Cotizaciones stock = findStockByDate(nextDay, cotizaciones);

		// Si no se encuentra la cotización y no se ha llegado al límite de
		// busqueda,
		// la función se llama a sí misma para seguir buscando y reduce el
		// límite en un intento.
		if (stock == null && limit > 0) {
			return findNextDayWithStock(nextDay, cotizaciones, limit - 1);
		}
		return stock;
	}

	/// Busca en un listado de cotizaciones una cotización según la fecha.
	public Cotizaciones findStockByDate(LocalDate fecha,
			List<Cotizaciones> cotizaciones) {
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

		// buscar un jueves y el siguiente dia, si no lo encuentra buscar el
		// siguiente.
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
