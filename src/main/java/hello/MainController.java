package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/blackboard/") // This means URL's start with /blackboard (after Application path)
public class MainController {
	// This means to get the bean called blackboardRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private final BlackboardRepository blackboardRepository;
	private final static int MAXIMUM_ALLOWED_BLACKBOARDS = 40;

	@Autowired
	public MainController(BlackboardRepository blackboardRepository) {
		this.blackboardRepository = blackboardRepository;
	}

	// Shows all existing blackboards
	@GetMapping(path = "/show_blackboards")
	public ResponseEntity  getAllBlackboards() {
		// This returns a ResponseEntity which includes a JSON List of blackboards and the HttpStatus
        if (blackboardRepository.count() == 0) {
            return new ResponseEntity<>("No Blackboards exist", HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<>(blackboardRepository.findAll(), HttpStatus.OK);
        }
	}

	// Checks if blackboard exists
	@GetMapping(path="/exists_blackboard/{name}")
	public ResponseEntity existsBlackboard (@PathVariable String name){
		// This returns a ResponseEntity which includes a String and the HttpStatus
		// Gets the name from the path

		if(name.length()>64){
			return new ResponseEntity<>("The size limit of the blackboard name was exceeded (64 characters)",HttpStatus.BAD_REQUEST);
		}

		boolean blackboard_exists = blackboardRepository.existsByName(name);
		if (blackboard_exists){
			return new ResponseEntity<>("Blackboard '" + name + "' exists.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Blackboard '" + name + "' does not exist.", HttpStatus.OK);
		}
	}

	// Creates a new blackboard
	@PostMapping(path="/create_blackboard") //
	public ResponseEntity createBlackboard (@RequestBody String name) {
		// This returns a ResponseEntity which includes a String and the HttpStatus
		// Gets the name from the body
		if(name.length()>64){
			return new ResponseEntity<>("The size limit of the blackboard name was exceeded (64 characters)",HttpStatus.BAD_REQUEST);
		}

		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (blackboardRepository.count() >= MAXIMUM_ALLOWED_BLACKBOARDS) {
			return new ResponseEntity<>("Blackboard exceeds maximum number ("+MAXIMUM_ALLOWED_BLACKBOARDS+") of allowed blackboards", HttpStatus.CONFLICT);
		} else {
			if (existsBlackboard){
				return new ResponseEntity<>("Blackboard '" + name + "' already exist", HttpStatus.CONFLICT);
			} else {
				Blackboard n = new Blackboard();
				n.setName(name);
				blackboardRepository.save(n);
				return new ResponseEntity<>("Saved Blackboard", HttpStatus.CREATED);
			}
		}
	}

	// Adds a message to a blackboard
	@PutMapping(path="/display_blackboard/{name}") // Map ONLY GET Requests
	public ResponseEntity displayBlackboard (@PathVariable String name, @RequestBody String message) {
		// This returns a ResponseEntity which includes a String and the HttpStatus
		// Gets the name from the path and the message from the body
		if(name.length()>64 || message.length()>255){
			return new ResponseEntity<>("The size limit of the blackboard name was exceeded (64 characters)\n Or the size limit of the blackboard message was exceeded (255 characters)",HttpStatus.BAD_REQUEST);
		}

		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			Blackboard n = new Blackboard();
			n.setName(name);
			n.setMessage(message);
			blackboardRepository.save(n);
			return new ResponseEntity<>("Blackboard was successfully displayed.",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Blackboard '" + name + "' does not exist",HttpStatus.CONFLICT);
		}
	}

	// Reads a message from a specific blackboard
	@GetMapping(path="/read_blackboard/{name}")
	public ResponseEntity readBlackboard (@PathVariable String name) {
		// This returns a ResponseEntity which includes a String and the HttpStatus
		// Gets the name from the path
		if(name.length()>64){
			return new ResponseEntity<>("The size limit of the blackboard name was exceeded (64 characters)",HttpStatus.BAD_REQUEST);
		}
        boolean existsBlackboard = blackboardRepository.existsByName(name);
        if (existsBlackboard) {
			Blackboard b = blackboardRepository.findOneByName(name);
        	if (b.getMessage() == null) {
                return new ResponseEntity<>("Blackboard '" + name + "' is empty", HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>("The requested message is: " + b.getMessage(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Blackboard '" + name + "' does not exist", HttpStatus.CONFLICT);
        }
    }

	// Clears the message of a specific blackboard
	@PutMapping(path="/clear_blackboard/{name}")
	public ResponseEntity<String> clearedBlackboard (@PathVariable String name){
		// This returns a ResponseEntity which includes a String and the HttpStatus
		// Gets the name from the path
		if(name.length()>64){
			return new ResponseEntity<>("The size limit of the blackboard name was exceeded (64 characters)",HttpStatus.BAD_REQUEST);
		}
		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			Blackboard n = blackboardRepository.findOneByName(name);
			n.setMessage(null);
			blackboardRepository.save(n);
			return new ResponseEntity<>("Cleared blackboard: '"+name+"'", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Blackboard '" + name + "' does not exist",HttpStatus.CONFLICT);
		}
	}

	// Deletes a specific Blackboard completely
	@DeleteMapping(path="/delete_blackboard/{name}")
	public ResponseEntity<String> deletedBlackboard (@PathVariable String name){
		// This returns a ResponseEntity which includes a String and the HttpStatus
		// Gets the name from the path
		if(name.length()>64){
			return new ResponseEntity<>("The size limit of the blackboard name was exceeded (64 characters)",HttpStatus.BAD_REQUEST);
		}
		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			blackboardRepository.deleteBlackboardByName(name);
			return new ResponseEntity<>("Deleted blackboard '" + name +"'.",HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Blackboard '" + name + "' does not exist",HttpStatus.CONFLICT);
		}
	}

	// Checks if a blackboard message is empty
	@GetMapping(path="/blackboard_status/{name}")
	public ResponseEntity<String> isEmptyBlackboard (@PathVariable String name){
		// This returns a ResponseEntity which includes a String and the HttpStatus
		// Gets the name from the path
		if(name.length()>64){
			return new ResponseEntity<>("The size limit of the blackboard name was exceeded (64 characters)",HttpStatus.BAD_REQUEST);
		}
		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			Blackboard b = blackboardRepository.findOneByName(name);
			if (b.getMessage() == null){
				return new ResponseEntity<>("Blackboard '"+ name+ "' has no message.", HttpStatus.OK) ;
			} else {
				return new ResponseEntity<>("Blackboard '"+name+ "' contains a message.",HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>("Blackboard '"+ name+ "' does not exist.", HttpStatus.CONFLICT);
		}
	}
}
