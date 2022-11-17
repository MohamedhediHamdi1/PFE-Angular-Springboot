package Cryptoo.com.example.Cryptoo.responses;

public class UserResponse {
	
	private String userId;

	private String username;

	private Boolean admin;

	private ContactRsponse contact;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ContactRsponse getContact() {
		return contact;
	}

	public void setContact(ContactRsponse contact) {
		this.contact = contact;
	}


	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


}
