package evaluacion.escuela.com.negocio.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalificacionesAlumnoDto   {
private List<MateriasAlumnoDto> calificaciones;
private double promedio;
}
