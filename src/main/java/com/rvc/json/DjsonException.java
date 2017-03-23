package com.rvc.json;

/**
 * @author Nurmuhammad
 */

public class DjsonException extends RuntimeException {
    public DjsonException() {
    }

    public DjsonException(String message) {
        super(message);
    }

    public DjsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public DjsonException(Throwable cause) {
        super(cause);
    }

    public DjsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
