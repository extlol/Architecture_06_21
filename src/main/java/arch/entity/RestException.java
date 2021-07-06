package arch.entity;

import java.io.Serial;

public class RestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public RestException(String message) {
        super(message);
    }
}
