package com.mk.mnx.infr.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class HttpCodeException  extends HttpStatusCodeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5792753491292099178L;
	private List<String> messages ;
	
	public HttpCodeException(HttpStatus statusCode ) {
		super(statusCode);
	}
	
	public HttpCodeException(HttpStatus statusCode, String message) {
		super(statusCode ,message);
	}
	
	public HttpCodeException(HttpStatus statusCode , String message, List<String> messages) {
		super(statusCode,message);
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
