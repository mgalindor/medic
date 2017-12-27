package com.mk.mnx.infr.exception;

public class HttpCodeException  extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5792753491292099178L;
	int httpCode;

	public HttpCodeException(int httpCode) {
		super();
		this.httpCode = httpCode;
	}
	
	public HttpCodeException(int httpCode , String message) {
		super(message);
		this.httpCode = httpCode;
	}
	
	public HttpCodeException(int httpCode , Throwable t) {
		super(t);
		this.httpCode = httpCode;
	}
	
	public HttpCodeException(int httpCode , String message , Throwable t) {
		super(message,t);
		this.httpCode = httpCode;
	}
	
	public int getHttpCode() {
		return httpCode;
	}
	
}
