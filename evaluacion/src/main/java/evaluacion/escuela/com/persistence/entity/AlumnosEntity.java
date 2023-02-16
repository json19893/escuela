package evaluacion.escuela.com.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tbl_alumnos")
@Data
@NoArgsConstructor
public class AlumnosEntity {
	@Id
	private String id;
	private String nombre;
	private String ap_paterno;
	private String ap_materno;
	private long activo;
	

}
