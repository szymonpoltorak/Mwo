package razepl.dev.mwobackend.exceptions.auth;

import java.io.Serial;

public class InvalidTokenException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = 2384137801317432740L;

    public InvalidTokenException(String message) {
        super(message);
    }
}
