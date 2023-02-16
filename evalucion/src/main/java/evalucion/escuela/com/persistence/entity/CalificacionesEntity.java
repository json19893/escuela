package evalucion.escuela.com.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tbl_calificaciones")
@Data
@NoArgsConstructor
public class CalificacionesEntity {
	@Id
	private String id;
	private String id_materias;
	private String id_usuario;
	private String fecha_registro;
	private Double calificacion;
	

}
