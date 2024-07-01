package com.github.thiagosousagarcia.sistemavendas.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.thiagosousagarcia.sistemavendas.validation.constraintvalidator.NotEmptyListValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
	
	String message() default  "A lista não pode está vazia";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
