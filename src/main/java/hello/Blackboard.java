package hello;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Blackboard {

//	@GeneratedValue(strategy=GenerationType.AUTO)
// 	private Integer id;
	@Id
	@Column(length = 64)
    private String name;
	@Column(length = 255)
    private String message;





//	public void setId(Integer id) {
//		this.id = id;
//	}

//	public Integer getId() {
//		return id;
//	}

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

