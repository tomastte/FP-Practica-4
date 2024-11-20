import java.util.Scanner;

public class Teclado {
	private static Scanner entrada = new Scanner(System.in);

	public static int leerEntero(int menor, int mayor, String mensaje) {
		int resultado = 0;
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			if (entrada.hasNextInt()) {
				resultado = entrada.nextInt();
				correcto = resultado >= menor && resultado <= mayor;
			}
			entrada.nextLine();
		} while (!correcto);
		return resultado;
	}

	public static String leerString(String mensaje) {
		System.out.print(mensaje);
		String resultado = entrada.next();
		entrada.nextLine();
		return resultado;
	}

	public static char leerSiNo(String mensaje) {
		char resultado = ' ';
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			String cadena = entrada.nextLine();
			cadena = cadena.toUpperCase();
			if (cadena.length() == 1 && cadena.charAt(0) == 'S') {
				resultado = 'S';
				correcto = true;
			} else if (cadena.length() == 1 && cadena.charAt(0) == 'N') {
				resultado = 'N';
				correcto = true;
			}
		} while (!correcto);
		return resultado;
	}

	public static String leerJugadaGuardar(int longitud, String mensaje) {
		String resultado = "";
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			String cadena = entrada.nextLine();
			cadena = cadena.toUpperCase();
			if (cadena.equals("G")) {
				correcto = true;
				resultado = "G";
			} else if (cadena.length() == longitud) {
				int i = 0;
				boolean esJugada = true;
				while (i < longitud && esJugada) {
					char car = cadena.charAt(i);
					if (car != 'R' && car != 'V' && car != 'A' && car != 'P') {
						esJugada = false;
					}
					i++;
				}
				if (esJugada) {
					correcto = true;
					resultado = cadena;
				}
			}
		} while (!correcto);
		return resultado;
	}

}
