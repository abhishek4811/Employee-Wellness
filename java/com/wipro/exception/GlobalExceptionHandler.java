package com.wipro.exception;

public class GlobalExceptionHandler extends Exception{
	public GlobalExceptionHandler() {
		super("emplyee exception");
	}
	public GlobalExceptionHandler(String message) {
		super(message);
	}
	public GlobalExceptionHandler(String message , Throwable t) {
		super(message,t);
	}
}
