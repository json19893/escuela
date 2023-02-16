package evaluacion.escuela.com.negocio.service;

import java.io.IOException;
import java.util.List;

import evaluacion.escuela.com.negocio.dto.CalificacionesAlumnoDto;
import evaluacion.escuela.com.negocio.dto.GenericResponseDto;
import evaluacion.escuela.com.negocio.dto.RequestCalificationDto;
import evaluacion.escuela.com.persistence.entity.AlumnosEntity;
import evaluacion.escuela.com.persistence.entity.MateriasEntity;


public interface IescuelaService {
	GenericResponseDto putCalificacion(RequestCalificationDto data) throws IOException ;
	CalificacionesAlumnoDto getCalificaciones(String idAlumno) throws IOException ;
	GenericResponseDto dropCalificacion(String idCalificacion)throws IOException ;
	List<AlumnosEntity> getAlumnos() throws IOException ;
	List<MateriasEntity> getMaterias() throws IOException ;
}
