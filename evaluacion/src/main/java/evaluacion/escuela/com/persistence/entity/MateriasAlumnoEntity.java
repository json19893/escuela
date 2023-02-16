package evaluacion.escuela.com.persistence.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
@Document(collection = "vw_calificacion_alumno")
@Data
@NoArgsConstructor
public class MateriasAlumnoEntity {
	private String idUsuario;
	private String nombre;
	private String apellido;
	private String materia;
	private Double calificacion;
	private String fechaRegistro;

}
