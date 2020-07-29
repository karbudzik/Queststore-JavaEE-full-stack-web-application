package exception;

public class EmailFormatException extends Exception {
    public EmailFormatException(int min, int max) {
        super(String.format("Email incorrect. Should contain from %d to %d characters, no special signs. @ is required.", min, max));
    }
}
