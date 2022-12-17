package Cryptoo.com.example.Cryptoo.responses;

public class UserResponse {
	
	private String userId;

	private String username;

	private Boolean admin;

	private String country;
	private String nb_ads;
	private String options1;

	private ContactRsponse contact;

	private PnlResponse pnl;


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
	public PnlResponse getPnl() {
		return pnl;
	}
	public void setPnl(PnlResponse pnl) {
		this.pnl = pnl;
	}
}
