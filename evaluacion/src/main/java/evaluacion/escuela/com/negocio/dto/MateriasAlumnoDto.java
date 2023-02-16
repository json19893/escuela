package evaluacion.escuela.com.negocio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MateriasAlumnoDto {
	private String idUsuario;
	private String nombre;
	private String apellido;
	private String materia;
	private Double calificacion;
	private String fechaRegistro;

}
