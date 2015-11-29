package pl.musicland.others;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

//MultipartFile oznacza dla jakiego typu danych będziemy przeprowadzać walidację
public class ImageValidatorImpl implements ConstraintValidator<ImageValidator, MultipartFile>{
	
	//Pozwala przekazać  wartość przez adnotacje np @Adnotation("adsfas")
	public void initialize(ImageValidator arg0) {
		 
    }
	
	//W tej funcji przeprowadzamy walidację 
	@Override
	public boolean isValid(MultipartFile image, ConstraintValidatorContext context) {
		
		return validateImage(image,context);
		
	}
	
	private boolean validateImage (MultipartFile image, ConstraintValidatorContext context) {
		if(image.getContentType().equals("image/jpeg")) {
			
			return true;
			
		} else {
			
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Wymagany format pliku to .jpg").addConstraintViolation();
			return false;
			
		}
	}
}
