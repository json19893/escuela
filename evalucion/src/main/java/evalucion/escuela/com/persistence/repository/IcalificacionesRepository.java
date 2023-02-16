package evalucion.escuela.com.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import evalucion.escuela.com.persistence.entity.CalificacionesEntity;

@Repository
public interface IcalificacionesRepository extends MongoRepository<CalificacionesEntity, String> {



}
