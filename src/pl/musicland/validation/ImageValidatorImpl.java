package pl.musicland.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

//MultipartFile oznacza dla jakiego typu danych będziemy przeprowadzać walidację
public class ImageValidatorImpl implements ConstraintValidator<ImageValidator, MultipartFile> {

	// Pozwala przekazać wartość przez adnotacje np @Adnotation("adsfas")
	public void initialize(ImageValidator arg0) {

	}

	// W tej funcji przeprowadzamy walidację
	@Override
	public boolean isValid(MultipartFile image, ConstraintValidatorContext context) {

		return validateImage(image, context);

	}

	private boolean validateImage(MultipartFile image, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (image.getContentType().equals("image/jpeg")) {
			return true;
		} else if (image.getSize() > 50000) {
			context.buildConstraintViolationWithTemplate("Rozmiar pliku jest za duży").addConstraintViolation();
			return false;
		} else {
			context.buildConstraintViolationWithTemplate("Wymagany format pliku to .jpg").addConstraintViolation();
			return false;

		}
	}
}
