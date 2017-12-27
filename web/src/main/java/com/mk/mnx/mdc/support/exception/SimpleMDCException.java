package com.mk.mnx.mdc.support.exception;

public class SimpleMDCException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5113798569066624619L;

	
	public SimpleMDCException() {
		super();
	}
	
	public SimpleMDCException(String message) {
		super(message);
	}
	
	public SimpleMDCException(Throwable t) {
		super(t);
	}
	
	public SimpleMDCException(String message , Throwable t) {
		super(message,t);
	}
}
