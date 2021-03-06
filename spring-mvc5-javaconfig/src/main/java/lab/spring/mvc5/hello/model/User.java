package lab.spring.mvc5.hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="USER_NAME")
	@Size(max=20,min=3,message="{user.name.invalid}")
	@NotEmpty(message="Please enter your name")
	private String name;
	
	@Column(name="USER_EMAIL")
	@Email(message="{user.email.invalid")
	@NotEmpty(message="Please enter your email")
	private String email;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

}
