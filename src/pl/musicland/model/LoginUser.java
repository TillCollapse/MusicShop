package pl.musicland.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class LoginUser {

	@NotBlank(message = "{empty_field_error_message}")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "{email_invalid_error_message}")
	private String email;

	@NotBlank(message = "{empty_field_error_message}")
	@Size(min = 8, message = "{min_size_error_message}")
	private String haslo;

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
