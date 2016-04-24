package pl.musicland.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class IsAlbumValidatorImpl implements ConstraintValidator<IsAlbumValidator, Object> {

	private String fieldName;
	private String dependFieldName1;
	private String dependFieldName2;
	private String dependFieldName3;
	private String dependFieldName4;

	@Override
	public void initialize(IsAlbumValidator constraintAnnotation) {
		fieldName = constraintAnnotation.fieldName();
		dependFieldName1 = constraintAnnotation.dependFieldName1();
		dependFieldName2 = constraintAnnotation.dependFieldName2();
		dependFieldName3 = constraintAnnotation.dependFieldName3();
		dependFieldName4 = constraintAnnotation.dependFieldName4();

	}

	@Override
	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		try {
			final String fieldValue = BeanUtils.getProperty(value, fieldName);
			final String dependFieldVaule1 = BeanUtils.getProperty(value, dependFieldName1);
			final String dependFieldVaule2 = BeanUtils.getProperty(value, dependFieldName2);
			final String dependFieldVaule3 = BeanUtils.getProperty(value, dependFieldName3);
			final String dependFieldVaule4 = BeanUtils.getProperty(value, dependFieldName4);
			if (fieldValue.equals("true")) {

				if (dependFieldVaule1.isEmpty()) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("{empty_field_error_message}")
							.addNode(dependFieldName1).addConstraintViolation();
					return false;
				} else if (dependFieldVaule2.isEmpty() && dependFieldVaule3.isEmpty() && dependFieldVaule4.isEmpty()) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("{empty_field_error_message}")
							.addNode(dependFieldName2).addConstraintViolation();
					context.buildConstraintViolationWithTemplate("{empty_field_error_message}")
							.addNode(dependFieldName3).addConstraintViolation();
					context.buildConstraintViolationWithTemplate("{empty_field_error_message}")
							.addNode(dependFieldName4).addConstraintViolation();
					return false;
				} else
					if (dependFieldVaule4.isEmpty() && (dependFieldVaule2.isEmpty() || dependFieldVaule3.isEmpty())) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("{author_name_and_surname_error_message}")
							.addNode(dependFieldName3).addConstraintViolation();
					return false;
				}

			}

		} catch (final Exception ignore) {

		}

		return true;
	}

}
