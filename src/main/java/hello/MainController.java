package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/blackboard/") // This means URL's start with /blackboard (after Application path)
public class MainController {
	@Autowired // This means to get the bean called blackboardRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private BlackboardRepository blackboardRepository;

	/*@PostMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewBlackboard (@RequestParam String name
			, @RequestParam String message) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Blackboard n = new Blackboard();
		n.setName(name);
		n.setMessage(message);
		blackboardRepository.save(n);
		return "Saved";
	}*/

	@GetMapping(path = "/show_blackboards")
	public ResponseEntity  getAllBlackboards() {

		return new ResponseEntity(blackboardRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(path="/exists_blackboard/{name}")
	public @ResponseBody String existsBlackboard (@PathVariable String name){
		// This returns a JSON or XML with the Blackboards

		boolean b = blackboardRepository.existsByName(name);
		String s = "false";
		if (b){
			s = "true";
		} else if (!b){
			s = "false";
		}
		return s;
	}

	//@GetMapping(path="/create_blackboard") // Map ONLY GET Requests
	@PostMapping(path="/create_blackboard/{name}") //
	public ResponseEntity createBlackboard (@PathVariable String name) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			return new ResponseEntity("Blackboard " + name + " already exists", HttpStatus.CONFLICT);
		} else {

			Blackboard n = new Blackboard();
			n.setName(name);
			blackboardRepository.save(n);
			return new ResponseEntity("Saved Blackboard", HttpStatus.CREATED);
		}
	}

	@PutMapping(path="/display_blackboard/{name}/{message}") // Map ONLY GET Requests
	public @ResponseBody String displayBlackboard (@PathVariable String name
			, @PathVariable String message) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			Blackboard n = new Blackboard();
			n.setName(name);
			n.setMessage(message);
			blackboardRepository.save(n);
			return "displayed blackboard";
		} else {

			return "Blackboard " + name + " does not exists";
		}
	}

	@GetMapping(path="/read_blackboard/{name}")
	public @ResponseBody String getMessage (@PathVariable String name) {
        //

        boolean existsBlackboard = blackboardRepository.existsByName(name);
        if (existsBlackboard) {
            if (isEmptyBlackboard(name).equals("true")) {
                return "Blackboard " + name + " is empty";
            } else {
                Blackboard b = blackboardRepository.findOneByName(name);
                return b.getMessage();
            }

        } else {
            return "Blackboard " + name + " does not exists";
        }


    }

	@GetMapping(path="/clear_blackboard/{name}")
	public @ResponseBody String clearedBlackboard (@PathVariable String name){
		// This returns a JSON or XML with the Blackboards
		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			Blackboard n = blackboardRepository.findOneByName(name);
			n.setMessage(null);
			blackboardRepository.save(n);
			return "cleared blackboard";
		} else {

			return "Blackboard " + name + " does not exists";
		}


	}



	@DeleteMapping(path="/delete_blackboard/{name}")
	public @ResponseBody String deletedBlackboard (@PathVariable String name){
		// This returns a JSON or XML with the Blackboards

		blackboardRepository.deleteBlackboardByName(name);
		return "Deleted blackboard " + name;
	}

	@GetMapping(path="/blackboard_status/{name}")
	public @ResponseBody String isEmptyBlackboard (@PathVariable String name){
		// This returns a JSON or XML with the Blackboards
		boolean existsBlackboard = blackboardRepository.existsByName(name);
		if (existsBlackboard){
			Blackboard b = blackboardRepository.findOneByName(name);
			if (b.getMessage() == null){
				return "true";
			} else {
				return "false";
			}
		} else {
			return "Blackboard " + name + " does not exists";
		}
	}
}
