package Cryptoo.com.example.Cryptoo.requests;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	//@Pattern(regexp = " [A-Z0-9]+")
	@NotNull(message = "CE champ est obligatoire")
	private String email;
	@Size(min=8,max=20,message = "Min 8 charecter")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$",message = "Wrong password format")
	private String password;
	private String firstname;
	private String lastname;
	private String phone;
	private String country;
	private String state;
	private String city;
	private String zipcode;
	private String address1;
	private String address2;
	private String prestataireID;
	private boolean membership;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPrestataireID() {
		return prestataireID;
	}

	public void setPrestataireID(String prestataireID) {
		this.prestataireID = prestataireID;
	}

	public boolean getMembership() {
		return membership;
	}

	public void setMembership(boolean membership) {
		this.membership = membership;
	}
}
