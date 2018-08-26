package pl.szczygielski.exception;

public class SeanceIsFullException extends RuntimeException {

    public SeanceIsFullException() {
    }

    public SeanceIsFullException(final String message) {
        super(message);
    }

    public SeanceIsFullException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SeanceIsFullException(final Throwable cause) {
        super(cause);
    }
}
