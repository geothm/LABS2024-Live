package ro.irian.labs2024.pizza.dto;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    private static final String NAME_PATTERN = "^[A-Za-z\\s]+$";
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 50;

    @Override
    public void initialize(ValidName constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) {
            return false;
        }
        return name.matches(NAME_PATTERN) && name.length() >= MIN_LENGTH && name.length() <= MAX_LENGTH;
    }
}
