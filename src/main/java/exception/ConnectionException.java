package exception;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
