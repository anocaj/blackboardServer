package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

// trial to implement the Repository class for Log (actually not necessary? only storing is needed for REST-API)
public interface LogRepository extends CrudRepository<Log, Long> {

//    @Transactional
//    save(Log entity);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Log b WHERE b.address = :address")
    boolean existsByAddress(@Param("address") String address);

}
