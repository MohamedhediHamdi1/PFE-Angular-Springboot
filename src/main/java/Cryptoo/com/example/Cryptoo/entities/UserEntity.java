package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name="users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = -8704195227248241243L;
	
	@Id()
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String userId;
	

	
	@Column(nullable=false, length =20,unique=true)
	private String username;



	@NotNull
	private Boolean admin;

	@Column(nullable=false)
	private String encryptedPassword;



	@NotNull
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private ContactEntity contact;






	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "users")
	private Set<GroupEntity> groups = new HashSet<>();




	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}


	public ContactEntity getContact() {
		return contact;
	}

	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}





}
