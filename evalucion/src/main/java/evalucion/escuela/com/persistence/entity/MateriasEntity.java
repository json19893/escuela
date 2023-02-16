package evalucion.escuela.com.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tbl_materias")
@Data
@NoArgsConstructor
public class MateriasEntity {
	@Id
	private String id;
	private String nombre;
	private long activo;
	

}
