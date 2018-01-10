package com.mk.mnx.infr.exception;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class HttpCodeException  extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5792753491292099178L;
	private int httpCode;
	private List<String> messages ;
	
	public static HttpServletResponse CODES;
	
	public HttpCodeException(int httpCode) {
		super();
		this.httpCode = httpCode;
	}
	
	public HttpCodeException(int httpCode , String message) {
		super(message);
		this.httpCode = httpCode;
	}
	
	public HttpCodeException(int httpCode , String message, List<String> messages) {
		super(message);
		this.httpCode = httpCode;
		this.messages = messages;
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
	
	public List<String> getMessages() {
		return messages;
	}
}
