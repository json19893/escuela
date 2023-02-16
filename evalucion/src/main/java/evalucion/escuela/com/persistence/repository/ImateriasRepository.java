package evalucion.escuela.com.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import evalucion.escuela.com.persistence.entity.MateriasEntity;
@Repository
public interface ImateriasRepository extends MongoRepository<MateriasEntity, String> {

}
