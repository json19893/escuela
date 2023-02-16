package evalucion.escuela.com.presentacion.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evalucion.escuela.com.negocio.dto.CalificacionesAlumnoDto;
import evalucion.escuela.com.negocio.dto.GenericResponseDto;
import evalucion.escuela.com.negocio.dto.RequestCalificationDto;
import evalucion.escuela.com.negocio.service.IescuelaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class EscuelaController {
	   @Autowired
	   IescuelaService escuelaService;
	@PostMapping("/putCalificacion")
	public GenericResponseDto putCalificacion(@RequestBody(required = true) RequestCalificationDto data)
			throws IOException {
		return escuelaService.putCalificacion(data);
	}
	@GetMapping("/getCalificaciones")
	public CalificacionesAlumnoDto getCalificaciones(@RequestParam(required = true) String idAlumno)
			throws IOException {
		return escuelaService.getCalificaciones(idAlumno);
	}

}
