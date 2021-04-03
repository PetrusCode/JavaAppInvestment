package com.netmind.presentation;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
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
								+ "\n 1.Invertir en (ï¿½)" + "\n 2.Exit"));
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
		System.out.println("1.Invertir en (ï¿½)");
	}

	public static <Scansner> void completeInvestiment(Investors investors,
			Scansner scanner, Cotizaciones cotizaciones) throws ParseException,
			IllegalStateException, FileNotFoundException {
		System.out.println("---Invierta en euros---");
		System.out.println("Introduzca nombre:");
		investors.setName(((Scanner) scanner).nextLine());

		System.out.println("Introduca sus apellidos:");
		investors.setSurname(((Scanner) scanner).nextLine());

		System.out.println("Monto a invertir (ï¿½)");
		investors.setMoney(((Scanner) scanner).nextInt());

		System.out.println("Fecha de inicio de inversion");

		System.out.println("Fecha fin de inversion");

		// coti.setApertura(res);

		// System.out.println(dates.size());
		System.out.println(System.getProperty("java.version"));

	}

	@SuppressWarnings("static-access")
	public static double calcularGanancias() throws IllegalStateException,
			FileNotFoundException, ParseException {
		CotizacionesBl cotizacionesBL = new CotizacionesBl();

		List<Cotizaciones> cotizacionesList = CotizacionesDao.GetCotizaciones();

		Cotizaciones lastCot = cotizacionesList.get(0);
		double cierreLast = lastCot.getCierre();
		double valor = 0;
		int montoInversion = 50;
		double porcentajeBroker = 0.02;
		List<Cotizaciones> cotizaciones = cotizacionesBL
				.getNextDayQuotations(cotizacionesList);

		double lastCierre = cotizaciones.get(cotizaciones.size() - 1)
				.getCierre();
		for (Cotizaciones cotizacion : cotizaciones) {
			double apertura = cotizacion.getApertura();
			double divisor = montoInversion * porcentajeBroker;
			double montoInversionReal = montoInversion - divisor;
			double resultado = montoInversionReal / (apertura);

			System.out.println(cotizacion.toString());
			// Math.round(resultado * 1000d) / 1000d

			BigDecimal conversion = new BigDecimal(resultado).setScale(3,
					RoundingMode.HALF_UP);
			valor += conversion.doubleValue();
			BigDecimal conversion2 = new BigDecimal(valor).setScale(3,
					RoundingMode.HALF_UP);

			if (cotizacion.getCierre().equals(lastCierre)) {
				double capitalFinal = conversion2.doubleValue() * cierreLast;
				BigDecimal nuevaConversion = new BigDecimal(capitalFinal)
						.setScale(3, RoundingMode.HALF_UP);
				System.out.println(("Total acciones\n" + conversion2));
				System.out.println("Capital final\n" + (nuevaConversion) + "€");
			}

		}
		return valor;

	}

}