package one.digitalinnovation.personapi.exceptions;

public class RefreshTokenException extends RuntimeException {

    public RefreshTokenException() {
        super("Can't create a refresh token for the user!");
    }
}
