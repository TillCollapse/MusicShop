package pl.musicland.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { IsAlbumValidatorImpl.class })
public @interface IsAlbumValidator {

	String message() default "{IsAlbum error}";

	Class<?>[]groups() default {};

	Class<? extends Payload>[]payload() default {};

	String fieldName();

	String dependFieldName1();

	String dependFieldName2();

	String dependFieldName3();

	String dependFieldName4();

	@Documented
	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		IsAlbumValidator[]value();
	}

}
