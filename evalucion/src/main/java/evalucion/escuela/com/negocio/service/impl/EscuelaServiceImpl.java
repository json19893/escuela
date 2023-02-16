package evalucion.escuela.com.negocio.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evalucion.escuela.com.negocio.dto.CalificacionesAlumnoDto;
import evalucion.escuela.com.negocio.dto.GenericResponseDto;
import evalucion.escuela.com.negocio.dto.RequestCalificationDto;
import evalucion.escuela.com.negocio.service.IescuelaService;
import evalucion.escuela.com.negocio.utils.Utils;
import evalucion.escuela.com.negocio.utils.constants;
import evalucion.escuela.com.persistence.entity.CalificacionesEntity;
import evalucion.escuela.com.persistence.repository.IcalificacionesRepository;

@Service
public class EscuelaServiceImpl extends constants implements IescuelaService {

	@Autowired
	IcalificacionesRepository calificacioneRepository;

	@Override
	public GenericResponseDto putCalificacion(RequestCalificationDto data) throws IOException {

		boolean valCalificacion = Utils.valCalificacion(data.getCalificacion());
		if (valCalificacion) {
			return new GenericResponseDto(ERROR, SMS_CAL_ERRONEA);
		}
		Optional<CalificacionesEntity> calificacion = calificacioneRepository.findById(data.getIdCalificacion());
		if (data.getIdCalificacion().equals(null)) {
			calificacion.get().setCalificacion(data.getCalificacion());
			calificacion.get().setFecha_registro(Utils.formatoFecha(LocalDate.now()));
			calificacion.get().setId_materias(data.getIdMateria());
			calificacion.get().setId_usuario(data.getIdUsuario());
			calificacioneRepository.save(calificacion.get());
		}else {
			
			calificacion.get().setCalificacion(data.getCalificacion());
			calificacioneRepository.save(calificacion.get());
			return new GenericResponseDto(OK, SMS_EXITO_ACTU_CAL);
		}
		return new GenericResponseDto(OK, SMS_EXITO_REGISTRO_CAL);
	}

	@Override
	public CalificacionesAlumnoDto getCalificaciones(String idAlumno) throws IOException {
		
		return null;
	}

}
