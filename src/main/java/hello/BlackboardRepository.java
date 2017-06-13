package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BlackboardRepository extends CrudRepository<Blackboard, Long> {

    @Transactional
    Blackboard findOneByName(String name);

    @Transactional
    void deleteBlackboardByName(String name);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Blackboard b WHERE b.name = :name")
    boolean existsByName(@Param("name") String name);

    //@Query("SELECT CASE WHEN COUNT(b) > MAXIMUM_BLACKBOARD_NUMBER THEN true ELSE false END FROM Blackboard b")
    @Query("SELECT COUNT(b) FROM Blackboard b")
    int getBlackboardCount();
    //boolean exceedsBlackboardLimit();

}
