package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called blackboardRepository
// CRUD refers Create, Read, Update, Delete

// The BlackboardRepository implements the Database to Object binding,
// this means that the MySQL table blackboard entries are bound to Blackboard entries
public interface BlackboardRepository extends CrudRepository<Blackboard, Long> {

    // returns a Blackboard by using the name as input
    @Transactional
    Blackboard findOneByName(String name);

    // deletes a Blackboard entrie by using the name as input
    @Transactional
    void deleteBlackboardByName(String name);

    // checks on database if specific blackboard exists
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Blackboard b WHERE b.name = :name")
    boolean existsByName(@Param("name") String name);


}
