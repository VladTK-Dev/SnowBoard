package org.example.snowboard.rent.validators;

import org.example.snowboard.rent.exceptions.RegisterValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidator {
    public static void validateName(String name) throws RegisterValidationException {
        validateBasics(name);
    }

    public static void validateSurname(String surname) {
        validateBasics(surname);
    }

    private static void validateBasics(String value) {
        if(value == null) {
            throw new RegisterValidationException();
        }

        Pattern pattern = Pattern.compile("[A-Z][a-z]+");
        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches()) {
            throw new RegisterValidationException();
        }
    }
}
