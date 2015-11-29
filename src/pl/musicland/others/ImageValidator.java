package pl.musicland.others;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;  


@Documented

//Oznacza, że ta adnotacja przeznaczona jest dla pola
@Target({ElementType.METHOD, ElementType.FIELD})

//Określa jak ta adnotacja jest przechowywana, w tym wypadku w czasie wykonywania through refleksion
@Retention(RetentionPolicy.RUNTIME)  

//Określa, że walidator będzię używany za pomocą adnotacji @ImageValidator
@Constraint(validatedBy = {ImageValidatorImpl.class})

public @interface ImageValidator {
	
	//Definiuje domyślną wiadomośc dla tego walidatora
	String message() default "{ImageValidator Error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
