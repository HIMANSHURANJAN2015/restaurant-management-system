package exception;

public class UnAuthorizedAccess extends RuntimeException {

    public UnAuthorizedAccess() {

    }

    public UnAuthorizedAccess(String message) {
        super(message);
    }
}
