package mypackage.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeBoundsValidator.class)
public @interface DateTimeBounds {

    String message() default "{mypackage.constraints.DateTimeBounds.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String before();

    String after();
}
