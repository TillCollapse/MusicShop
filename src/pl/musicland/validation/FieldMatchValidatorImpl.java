package pl.musicland.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidatorImpl implements ConstraintValidator<FieldMatchValidator, Object> {

	private String firstFieldName;
	private String secondFiledname;
	private String errorMessageName;

	@Override
	public void initialize(final FieldMatchValidator constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFiledname = constraintAnnotation.second();
		errorMessageName = constraintAnnotation.errorMessage();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean toReturn = false;
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFiledname);

			toReturn = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
		} catch (final Exception ignore) {

		}

		if (!toReturn) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(errorMessageName).addNode(firstFieldName)
					.addConstraintViolation();
			context.buildConstraintViolationWithTemplate(errorMessageName).addNode(secondFiledname)
					.addConstraintViolation();
		}

		return toReturn;
	}

}
