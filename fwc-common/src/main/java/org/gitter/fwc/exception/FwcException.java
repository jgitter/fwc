package org.gitter.fwc.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class FwcException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FwcException(String message) {
        super(message);
    }

    public FwcException(String message, Throwable cause) {
        super(message, cause);
    }

    public FwcException(Throwable cause) {
        super(cause);
    }

}
