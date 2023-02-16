package evaluacion.escuela.com.negocio.service;

import java.io.IOException;

import evaluacion.escuela.com.negocio.dto.CalificacionesAlumnoDto;
import evaluacion.escuela.com.negocio.dto.GenericResponseDto;
import evaluacion.escuela.com.negocio.dto.RequestCalificationDto;

public interface IescuelaService {
	GenericResponseDto putCalificacion(RequestCalificationDto data) throws IOException ;
	CalificacionesAlumnoDto getCalificaciones(String idAlumno) throws IOException ;
	GenericResponseDto dropCalificacion(String idCalificacion)throws IOException ;
}
