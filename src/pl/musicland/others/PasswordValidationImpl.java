package pl.musicland.others;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidationImpl implements ConstraintValidator<PasswordValidation, Object> {

	 private String password;
	 private String repassword;
	 
	@Override
	public void initialize(PasswordValidation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
