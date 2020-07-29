package exception;

public class TypeFormatException extends Exception {
    public TypeFormatException(String type1, String type2) {
        super(String.format("You have to choose one of the options below: %s or %s", type1, type2));
    }
}
