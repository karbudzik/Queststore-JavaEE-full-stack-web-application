package validation;

import exception.*;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public void validateStringLength(String text, int min, int max) throws StringLengthFormatException {
        if (text.length() < min || text.length() > max) {
            throw new StringLengthFormatException(min, max);
        }
    }

    public void validateValue(String numberString, int min, int max) throws ValueFormatException {
        try {
            int value = Integer.parseInt(numberString);
            if (value < min || value > max) {
                throw new ValueFormatException(min, max);
            }
        } catch (NumberFormatException e) {
            throw new ValueFormatException(min, max);
        }
    }

    public void validateType(String text, String type1, String type2) throws TypeFormatException {
        if (text == null
                || (!text.toUpperCase().equals(type1.toUpperCase())
                && !text.toUpperCase().equals(type2.toUpperCase()))) {
            throw new TypeFormatException(type1, type2);
        }
    }

    public void validateDate(String text, String allowedFormat) throws DateFormatException {
        try {
            Date.valueOf(text);
        } catch (IllegalArgumentException e) {
            throw new DateFormatException(allowedFormat);
        }
    }

    public void validateEmail(String text, int min, int max) throws EmailFormatException {
        if ((text == null || text.length() < min || text.length() > max)) {
            throw new EmailFormatException(min, max);
        }

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if ((!matcher.matches())) {
            throw new EmailFormatException(min, max);
        }
    }
}
