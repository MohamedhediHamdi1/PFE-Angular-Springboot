package Cryptoo.com.example.Cryptoo.requests;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {


	//@Pattern(regexp = " [A-Z0-9]+")
	@NotNull(message = "CE champ est obligatoire")
	private String username;
	

	@Size(min=8,max=20,message = "Min 8 charecter")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$",message = "Wrong password format")
	private String password;

	private Boolean admin;

	private ContactRequest contact;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public ContactRequest getContact() {
		return contact;
	}

	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}



}
