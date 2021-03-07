package com.netmind.presentation;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.netmind.business.CotizacionesBl;
import com.netmind.dao.CotizacionesDao;
import com.netmind.model.Cotizaciones;
import com.netmind.model.Investors;

public class Menu {

	@SuppressWarnings("static-access")
	public static void investorsOptions() throws IllegalStateException,
			FileNotFoundException, ParseException {
		CotizacionesBl cotizacionesBl = new CotizacionesBl();
		Scanner scanner = new Scanner(System.in);

		int options = 0;

		do {
			home();

			try {
				options = Integer.parseInt(
						JOptionPane.showInputDialog("Opciones para invertir"
								+ "\n 1.Invertir en (�)" + "\n 2.Exit"));
			} catch (Exception e) {
				System.out.println(e.getMessage() + "Introduzca un numero");
				e.getStackTrace();
			}
			switch (options) {
			case 1:
				Investors investors = new Investors();
				Cotizaciones cotizaciones = new Cotizaciones();
				completeInvestiment(investors, scanner, cotizaciones);
				cotizacionesBl.addCotizaciones(cotizaciones);
				break;

			default:
				System.out.println("Gracias por invertir!");

				break;

			}
		} while (options != 2);
		scanner.close();
	}

	public static void home() {
		System.out.println("Opciones para invertir");
		System.out.println("1.Invertir en (�)");
	}

	public static <Scansner> void completeInvestiment(Investors investors,
			Scansner scanner, Cotizaciones cotizaciones) throws ParseException,
			IllegalStateException, FileNotFoundException {
		System.out.println("---Invierta en euros---");
		System.out.println("Introduzca nombre:");
		investors.setName(((Scanner) scanner).nextLine());

		System.out.println("Introduca sus apellidos:");
		investors.setSurname(((Scanner) scanner).nextLine());

		System.out.println("Monto a invertir (�)");
		investors.setMoney(((Scanner) scanner).nextInt());

		System.out.println("Fecha de inicio de inversion");

		System.out.println("Fecha fin de inversion");

		CotizacionesBl.getAllLastThursday();
		List<Cotizaciones> cotis = CotizacionesDao
				.GetCotizaciones(cotizaciones);
		Cotizaciones firstCot = cotis.get(0);
		Cotizaciones lastCot = cotis.get(cotis.size() - 1);
		List<LocalDate> dates = CotizacionesBl
				.getAllLastThursday(lastCot.getFecha(), firstCot.getFecha());

		int count = 1;
		for (LocalDate date : dates) {

			String apertura = findCotizacionPorFecha(date, cotis);
			System.out.println(count + " - " + apertura);
			count++;

		}

		System.out.println(dates.size());
		System.out.println(System.getProperty("java.version"));
	}

	@SuppressWarnings("static-access")
	public static String findCotizacionPorFecha(LocalDate fecha,
			List<Cotizaciones> cotizaciones) {
		String valor = "";
		int monto = 49;

		for (Cotizaciones coti : cotizaciones) {

			Double apertura = coti.getApertura();

			// int suma = monto / (apertura + apertura);
			// int res = suma;
			if (coti.getFecha().equals(fecha)) {
				valor = coti.toString();

			}
		}
		return valor;

	}
}