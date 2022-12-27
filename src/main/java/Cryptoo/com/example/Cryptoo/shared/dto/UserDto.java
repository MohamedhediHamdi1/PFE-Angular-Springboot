package Cryptoo.com.example.Cryptoo.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable{
	
	
	private static final long serialVersionUID = -8905386225630855095L;
	
	private long id;
	private String userId;
	private String username;

	private Boolean admin;

	private String country;
	private String nb_ads;
	private String options1;



	private String password;
	private String encryptedPassword;

	private ContactDto contact;

	private PnlDto pnl;

	private HistoryDto history;





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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ContactDto getContact() {
		return contact;
	}

	public void setContact(ContactDto contact) {
		this.contact = contact;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNb_ads() {
		return nb_ads;
	}

	public void setNb_ads(String nb_ads) {
		this.nb_ads = nb_ads;
	}

	public String getOptions1() {
		return options1;
	}

	public void setOptions1(String options1) {
		this.options1 = options1;
	}

	public PnlDto getPnl() {
		return pnl;
	}

	public void setPnl(PnlDto pnl) {
		this.pnl = pnl;
	}

	public HistoryDto getHistory() {
		return history;
	}

	public void setHistory(HistoryDto history) {
		this.history = history;
	}

}
