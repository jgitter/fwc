package org.gitter.fwc.exception;

public class FwcServiceException extends FwcException {

	private static final long serialVersionUID = 1L;

	public FwcServiceException(String message) {
		super(message);
	}

	public FwcServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public FwcServiceException(Throwable cause) {
		super(cause);
	}

}
