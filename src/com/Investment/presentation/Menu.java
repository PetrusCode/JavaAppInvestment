package com.Investment.presentation;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.Investment.model.Investors;

public class Menu {

	public static void investorsOptions() {
		Scanner scanner = new Scanner(System.in);

		int options = 0;

		do {
			home();

			try {
				options = Integer.parseInt(
						JOptionPane.showInputDialog("Opciones para invertir"
								+ "\n 1.Invertir en (€)" + "\n 2.Exit"));
			} catch (Exception e) {
				System.out.println(e.getMessage() + "Introduzca un numero");
				e.getStackTrace();
			}
			switch (options) {
			case 1:
				Investors investors = new Investors();
				completeInvestiment(investors, scanner);

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
		System.out.println("1.Invertir en (€)");
	}

	public static void completeInvestiment(Investors investors,
			Scanner scanner) {
		System.out.println("---Invierta en euros---");
		System.out.println("Introduzca nombre:");
		investors.setName(scanner.nextLine());

		System.out.println("Introduca sus apellidos:");
		investors.setSurname(scanner.nextLine());

		System.out.println("Monto a invertir (€)");
		investors.setMoney(scanner.nextInt());

		System.out.println("Fecha de inicio de inversion");

		System.out.println("Fecha fin de inversion");

	}
}
