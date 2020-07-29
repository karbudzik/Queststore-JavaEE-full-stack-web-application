package exception;

public class ValueFormatException extends Exception {
    public ValueFormatException(int min, int max) {
        super(String.format("Wrong format! Should be a number from %d to %d, without decimals and special characters.", min, max));
    }
}
