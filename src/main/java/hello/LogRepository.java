package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LogRepository extends CrudRepository<Log, Long> {

	// keep empty, since no access via REST-Api needed
	
}
