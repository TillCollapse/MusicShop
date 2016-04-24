package pl.musicland.model;

import org.hibernate.validator.constraints.NotBlank;
import pl.musicland.validation.FieldMatchValidator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldMatchValidator.List({
		@FieldMatchValidator(first = "email", second = "emailconfirm", errorMessage = "{not_same_email_error_message}"),
		@FieldMatchValidator(first = "haslo", second = "hasloconfirm", errorMessage = "{not_same_password_error_message}")

})
public class User {

	private int id;

	@NotBlank(message = "{empty_field_error_message}")
	@Size(min = 3, max = 20, message = "{wrong_size_error_message}")
	@Pattern(regexp = "^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{3,21}", message = "{only_letter_error_message}")
	private String imie;

	@NotBlank(message = "{empty_field_error_message}")
	@Size(min = 3, max = 20, message = "Nazwisko powino składać się z 3 do 20 znaków")
	@Pattern(regexp = "^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{3,21}", message = "{only_letter_error_message}")
	private String nazwisko;

	@NotBlank(message = "{empty_field_error_message}")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "{email_invalid_error_message}")
	private String email;

	@NotBlank(message = "{empty_field_error_message}")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "{email_invalid_error_message}")
	private String emailconfirm;

	@NotBlank(message = "{empty_field_error_message}")
	@Size(min = 8, message = "{min_size_error_message}")
	private String haslo;
	@NotBlank(message = "{empty_field_error_message}")
	@Size(min = 8, message = "{min_size_error_message}")
	private String hasloconfirm;

	@NotBlank(message = "{empty_field_error_message}")
	@Pattern(regexp = "^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}", message = "{only_letter_error_message}")
	private String miasto;

	@NotBlank(message = "{empty_field_error_message}")
	@Pattern(regexp = "[\\d]{2}-[\\d]{3}", message = "{wrong_postal_code_error_message}")
	private String kodpocztowy;

	@NotBlank(message = "{empty_field_error_message}")
	@Pattern(regexp = "^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}(..)[0-9]{1,}[A-Za-z]{0,1}\\\\{0,1}[1-9]{0,}", message = "{wrong_addres_error_message}")
	private String adres;

	private int enabled;

	public User() {

	}

	public User(int id, String imie, String nazwisko, String email, String haslo, String miasto, String kodpocztowy,
			String adres, int enabled) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.haslo = haslo;
		this.miasto = miasto;
		this.kodpocztowy = kodpocztowy;
		this.adres = adres;
		this.enabled = enabled;
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

	public int getEndabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
