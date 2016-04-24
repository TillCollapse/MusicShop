package pl.musicland.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.RetentionPolicy;

@Documented
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidatorImpl.class)
public @interface FieldMatchValidator {

	String message() default "{FieldMatch error}";

	Class<?>[]groups() default {};

	Class<? extends Payload>[]payload() default {};

	// zwraca pierwsze pole
	String first();

	// zwraca drugie pole
	String second();

	// zwraca error message
	String errorMessage();

	@Documented
	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		FieldMatchValidator[]value();
	}
}
