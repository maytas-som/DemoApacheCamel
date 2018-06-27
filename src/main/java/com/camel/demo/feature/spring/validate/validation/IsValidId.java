package com.camel.demo.feature.spring.validate.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IdValidator.class}
)
public @interface IsValidId {
    String message() default "Value is out of range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}