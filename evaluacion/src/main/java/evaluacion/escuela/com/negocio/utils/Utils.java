package evaluacion.escuela.com.negocio.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import evaluacion.escuela.com.negocio.dto.MateriasAlumnoDto;

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

	public static double calculaPromedio(List<MateriasAlumnoDto> materias) {	
			Integer totalMaterias=materias.size();
			double total=0.0;
			for(MateriasAlumnoDto m:materias) {
				total=total+m.getCalificacion();
			}
		return total/totalMaterias;
	}

}
