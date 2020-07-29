package exception;

public class StringLengthFormatException extends Exception {
    public StringLengthFormatException(int min, int max) {
        super(String.format("Given input has incorrect format. It should contain from %d to %d characters.", min, max));
    }
}
