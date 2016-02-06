package mypackage.constraints;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeBoundsValidator implements ConstraintValidator<DateTimeBounds, LocalDateTime> {

    private LocalDateTime lowerBound;
    private LocalDateTime upperBound;

    public void initialize(DateTimeBounds dateTimeBounds) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        this.lowerBound = LocalDateTime.parse(dateTimeBounds.after(), formatter);
        this.upperBound = LocalDateTime.parse(dateTimeBounds.before(), formatter);
    }

    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        if (localDateTime == null)
            return false;

        return localDateTime.isAfter(lowerBound) && localDateTime.isBefore(upperBound);
    }
}
