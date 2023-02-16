package evalucion.escuela.com.negocio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenericResponseDto {
	private String success;
	private String msg;
	 public GenericResponseDto(String success,String msg) {
	        this.success = success;
	        this.msg = msg;
	    }
}
