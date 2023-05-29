package nl.johanvanderklift.backendeindopdracht.exception;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
