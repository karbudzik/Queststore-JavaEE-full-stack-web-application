package exception;

public class DateFormatException extends Exception {
    public DateFormatException(String correctFormat) {
        super(String.format("Given input is incorrect. It should look like: %s", correctFormat));
    }
}
