package com.robinvandenhurk.onlinevotingplatform.servicevotingform.components;

import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.OR;

/**
 * Author:    Robin van den Hurk
 * Date:      25/03/2021
 * File name: NullOrNotEmpty
 */

@ConstraintComposition(OR)
@Null
@NotBlank
@ReportAsSingleViolation
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
public @interface NullOrNotEmpty {
    String message() default "{javax.validation.constraints.NotEmpty.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
