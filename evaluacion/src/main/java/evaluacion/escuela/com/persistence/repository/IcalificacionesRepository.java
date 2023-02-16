package evaluacion.escuela.com.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import evaluacion.escuela.com.persistence.entity.CalificacionesEntity;

@Repository
public interface IcalificacionesRepository extends MongoRepository<CalificacionesEntity, String> {
	@Query(value="{'id_materias': ?0, 'id_usuario': ?1}",count=true)
	Integer existeMateria(@Param("idMateria") String idMateria,@Param("idUsuario")String idUsuario);

}
