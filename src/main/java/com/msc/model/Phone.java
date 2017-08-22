package com.msc.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Paul on 02/08/2017.
 */
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "Must contain at least 10 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
