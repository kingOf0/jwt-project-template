package com.kingof0.jwtprojecttemplate.exception;

public class FileServeException extends RuntimeException {

	public FileServeException(String message) {
		super(message);
	}

	public FileServeException(String message, Throwable cause) {
		super(message, cause);
	}
}