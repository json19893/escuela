package evalucion.escuela.com.negocio.service;

import java.io.IOException;

import evalucion.escuela.com.negocio.dto.CalificacionesAlumnoDto;
import evalucion.escuela.com.negocio.dto.GenericResponseDto;
import evalucion.escuela.com.negocio.dto.RequestCalificationDto;

public interface IescuelaService {
	GenericResponseDto putCalificacion(RequestCalificationDto data) throws IOException ;
	CalificacionesAlumnoDto getCalificaciones(String idAlumno) throws IOException ;

}
