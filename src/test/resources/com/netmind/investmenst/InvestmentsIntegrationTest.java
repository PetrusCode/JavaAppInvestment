package com.netmind.investmenst;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import com.netmind.business.CotizacionesBl;
import com.netmind.dao.CotizacionesDao;
import com.netmind.model.Cotizaciones;

public class InvestmentsIntegrationTest {

	@Test
	public void testCalcularGanancias() throws IllegalStateException,
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

				assertTrue((conversion2.doubleValue()) == 1254.219);
				assertTrue((nuevaConversion.doubleValue()) == 36585.568);
			}

		}

		return;

	}

}