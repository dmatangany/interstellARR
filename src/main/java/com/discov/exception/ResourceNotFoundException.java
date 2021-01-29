package com.discov.exception;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -8659454849611061074L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

}