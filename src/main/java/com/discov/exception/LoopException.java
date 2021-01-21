package com.discov.exception;

public class LoopException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Source and Destination cannot be the same";
	}
}