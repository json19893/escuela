package evaluacion.escuela.com.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import evaluacion.escuela.com.negocio.dto.MateriasAlumnoDto;
import evaluacion.escuela.com.persistence.entity.MateriasAlumnoEntity;

@Repository
public interface IMateriasAlumnoRepository extends MongoRepository<MateriasAlumnoEntity, String> {
	@Aggregation(pipeline = { "{'$match':{'idUsuario':?0}}" })	
List<MateriasAlumnoDto> getMateriasByAlumno(@Param("idAlumno") String idAlumno);
}
