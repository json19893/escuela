package evaluacion.escuela.com.presentacion.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evaluacion.escuela.com.negocio.dto.CalificacionesAlumnoDto;
import evaluacion.escuela.com.negocio.dto.GenericResponseDto;
import evaluacion.escuela.com.negocio.dto.RequestCalificationDto;
import evaluacion.escuela.com.negocio.service.IescuelaService;
import evaluacion.escuela.com.persistence.entity.AlumnosEntity;
import evaluacion.escuela.com.persistence.entity.MateriasEntity;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class EscuelaController {
	@Autowired
	IescuelaService escuelaService;

	@PostMapping("/putCalificacion")
	public GenericResponseDto putCalificacion(@RequestBody RequestCalificationDto data) throws IOException {
		return escuelaService.putCalificacion(data);
	}

	@GetMapping("/getCalificaciones")
	public CalificacionesAlumnoDto getCalificaciones(@RequestParam(required = true) String idAlumno)
			throws IOException {
		return escuelaService.getCalificaciones(idAlumno);
	}

	@GetMapping("/dropCalificacion")
	public GenericResponseDto dropCalificacion(@RequestParam(required = true) String idCalificacion)
			throws IOException {
		return escuelaService.dropCalificacion(idCalificacion);
	}

	@GetMapping("/getAlumnos")
	public List<AlumnosEntity> getAlumnos() throws IOException {
		return escuelaService.getAlumnos();
	}

	@GetMapping("/getMaterias")
	public List<MateriasEntity> getMaterias() throws IOException {
		return escuelaService.getMaterias();
	}

}
