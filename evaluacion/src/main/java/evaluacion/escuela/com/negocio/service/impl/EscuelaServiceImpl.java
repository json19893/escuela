package evaluacion.escuela.com.negocio.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluacion.escuela.com.negocio.dto.CalificacionesAlumnoDto;
import evaluacion.escuela.com.negocio.dto.GenericResponseDto;
import evaluacion.escuela.com.negocio.dto.MateriasAlumnoDto;
import evaluacion.escuela.com.negocio.dto.RequestCalificationDto;
import evaluacion.escuela.com.negocio.service.IescuelaService;
import evaluacion.escuela.com.negocio.utils.Utils;
import evaluacion.escuela.com.negocio.utils.constants;
import evaluacion.escuela.com.persistence.entity.CalificacionesEntity;
import evaluacion.escuela.com.persistence.entity.MateriasAlumnoEntity;
import evaluacion.escuela.com.persistence.repository.IMateriasAlumnoRepository;
import evaluacion.escuela.com.persistence.repository.IcalificacionesRepository;

@Service
public class EscuelaServiceImpl extends constants implements IescuelaService {

	@Autowired
	IcalificacionesRepository calificacioneRepository;
	@Autowired
	IMateriasAlumnoRepository materiasAlumnoRepository;

	@Override
	public GenericResponseDto putCalificacion(RequestCalificationDto data) throws IOException {

		boolean valCalificacion = Utils.valCalificacion(data.getCalificacion());
		if (valCalificacion) {
			return new GenericResponseDto(ERROR, SMS_CAL_ERRONEA);
		}
		Optional<CalificacionesEntity> calificacion = Optional.ofNullable(new CalificacionesEntity());
		if (data.getIdCalificacion() == null) {
			calificacion.get().setCalificacion(data.getCalificacion());
			calificacion.get().setFecha_registro(Utils.formatoFecha(LocalDate.now()));
			calificacion.get().setId_materias(data.getIdMateria());
			calificacion.get().setId_usuario(data.getIdUsuario());
			calificacioneRepository.save(calificacion.get());
		} else {
			calificacion = calificacioneRepository.findById(data.getIdCalificacion());
			calificacion.get().setCalificacion(data.getCalificacion());
			calificacion.get().setFecha_registro(Utils.formatoFecha(LocalDate.now()));
			calificacioneRepository.save(calificacion.get());
			return new GenericResponseDto(OK, SMS_EXITO_ACTU_CAL);
		}
		return new GenericResponseDto(OK, SMS_EXITO_REGISTRO_CAL);
	}

	@Override
	public CalificacionesAlumnoDto getCalificaciones(String idAlumno) throws IOException {
		CalificacionesAlumnoDto response=new CalificacionesAlumnoDto();
		List<MateriasAlumnoDto> materias = materiasAlumnoRepository.getMateriasByAlumno(idAlumno);
		response.setCalificaciones(materias);
		response.setPromedio(Utils.calculaPromedio(materias));
		return response;
	}

	@Override
	public GenericResponseDto dropCalificacion(String idCalificacion) throws IOException {
			calificacioneRepository.deleteById(idCalificacion);
		 return new GenericResponseDto(OK, SMS_EXITO_DROP_CAL);
	}

}
