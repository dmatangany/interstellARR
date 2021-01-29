package com.discov.exception;

public class ControllerException extends Exception {

	private static final long serialVersionUID = -876180507998010368L;

	public ControllerException() {
		super();
	}

	public ControllerException(final String message) {
		super(message);
	}
}