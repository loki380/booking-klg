package loki381.bookingklg.exceptions;

public class ApartamentIsAlreadyBusyException extends RuntimeException {
    public ApartamentIsAlreadyBusyException() {
        super();
    }
    public ApartamentIsAlreadyBusyException(String message) {
        super(message);
    }
}
