package pl.musicland.model;

public class UserAuthority {

	private int userid;
	private String imie;
	private String nazwisko;
	private String email;

	private int authorityid;
	private String authority;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuthorityid() {
		return authorityid;
	}

	public void setAuthorityid(int authorityid) {
		this.authorityid = authorityid;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
