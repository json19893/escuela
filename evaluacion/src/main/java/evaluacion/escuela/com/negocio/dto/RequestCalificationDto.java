package evaluacion.escuela.com.negocio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCalificationDto {
	public String idMateria;
	public String idUsuario;
	public Double calificacion;
	public String idCalificacion; 

}
