package exception;

public class MaxAttemptsExceededException extends EWalletException{
    public MaxAttemptsExceededException(String message) {
        super(message);
    }
}
