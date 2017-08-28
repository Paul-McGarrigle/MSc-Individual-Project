package com.msc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Paul on 02/08/2017.
 */
// This entire Annotated Interface is taken from http://www.journaldev.com/2668/spring-validation-example-mvc-validator
// It essentially creates the custom Annotation @Phone which can then be referenced in other Classes
// any field with @Phone will be validated  by PhoneValidator Class
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "Must contain at least 10 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
