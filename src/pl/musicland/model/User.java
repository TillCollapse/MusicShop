package pl.musicland.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	private int id;
	
	@NotEmpty(message="Wypełnij to pole")
	@Size(min = 2, max = 20, message="Imię powino składać się z 3 do 20 znaków")
	@Pattern(regexp="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}", message="Użyj proszę wyłącznie liter")
	private String imie;
	
	@NotEmpty(message="Wypełnij to pole")
	@Size(min = 2, max = 20, message="Imię powino składać się z 3 do 20 znaków")
	@Pattern(regexp="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}", message="Użyj proszę wyłącznie liter")
	private String nazwisko;
	
	@NotEmpty(message="Wypełnij to pole")
	@Pattern(regexp=".+@.+\\.[a-z]+")
	private String email;
	@Pattern(regexp=".+@.+\\.[a-z]+")
	private String emailconfirm;
	
	@NotEmpty(message="Wypełnij to pole")
	@Size(min = 8, message="To pole wymaga minimum 8 zznaków")
	private String haslo;
	@NotEmpty(message="Wypełnij to pole")
	@Size(min = 8, message="To pole wymaga minimum 8 zznaków")
	private String hasloconfirm;
	
	@NotEmpty(message="Wypełnij to pole")
	@Pattern(regexp="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}", message="")
	private String miasto;
	
	@NotEmpty
	@Pattern(regexp="[\\d]{2}-[\\d]{3}", message="np.39-300")
	private String kodpocztowy;
	
	@NotEmpty(message="Wypełnij to pole")
	@Pattern(regexp="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}(..)[1-9]{1,}[A-Za-z]{0,1}\\{0,1}[1-9]{0,}", message="Wypełnij to pole w odpowiednim formacie np. Rakowicka 4\\5")
	private String adres;
	
	private int czyadmin;
	
	public User() {
	
	}
	
	public User(int id, String imie, String nazwisko, String email, String haslo, String miasto, String kodpocztowy, String adres,
			int czyadmin) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.haslo = haslo;
		this.miasto = miasto;
		this.kodpocztowy = kodpocztowy;
		this.adres = adres;
		this.czyadmin = czyadmin;
	}
	
	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMiasto() {
		return miasto;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	public String getKodpocztowy() {
		return kodpocztowy;
	}
	public void setKodpocztowy(String kodpocztowy) {
		this.kodpocztowy = kodpocztowy;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public int getCzyadmin() {
		return czyadmin;
	}
	public void setCzyadmin(int czyadmin) {
		this.czyadmin = czyadmin;
	}

	public String getEmailconfirm() {
		return emailconfirm;
	}

	public void setEmailconfirm(String emailconfirm) {
		this.emailconfirm = emailconfirm;
	}

	public String getHasloconfirm() {
		return hasloconfirm;
	}

	public void setHasloconfirm(String hasloconfirm) {
		this.hasloconfirm = hasloconfirm;
	}
	
}
