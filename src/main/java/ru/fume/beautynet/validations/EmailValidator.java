package ru.fume.beautynet.validations;

import ru.fume.beautynet.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_PATTERN =  "([A-Za-z0-9]+[\\-]?[A-Za-z0-9]+[\\.]?[A-Za-z0-9]+)+@";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return validEmail(email);
    }

    private boolean validEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }
}
