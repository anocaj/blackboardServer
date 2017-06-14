package hello;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Blackboard {

	@Id // sets primary key on name column
	@Column(length = 64) // maximal possible name size is set to 64
    private String name;

	@Column(length = 255) // maximal possible name size
    private String message;


    //standard getter and setter methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}

