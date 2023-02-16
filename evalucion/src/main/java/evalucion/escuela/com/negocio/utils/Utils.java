package evalucion.escuela.com.negocio.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static boolean valCalificacion(Double calificacion) {
		boolean response = false;
		if (calificacion > 10)
			response = true;
		
	return response;

	}
	public static String formatoFecha(LocalDate fecha) {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		return formatter.format(fecha);
		
	}

}
