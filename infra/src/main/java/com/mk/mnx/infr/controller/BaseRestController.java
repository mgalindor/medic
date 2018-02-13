package com.mk.mnx.infr.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.mnx.infr.constants.CommonConstants;
import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.infr.model.ErrorResponse;

public abstract class BaseRestController {
	
    protected final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGGER_COMTROLLER);

    protected final Logger loggerException = LoggerFactory.getLogger(CommonConstants.LOGGER_EXCEPTION);
    
    @Autowired
	private HttpServletRequest request;
   
    private String user;
    
    protected String getUser() {	
    	return user;
	}
    
    public HttpServletRequest getRequest() {
		return request;
	}
    
    @ExceptionHandler
    public void handleError(HttpServletRequest req, HttpServletResponse resp , Exception ex) throws IOException {
    	loggerException.error("Request: [{}] message: [{}]" , req.getRequestURL() , ex.getMessage());
    	loggerException.error("Error:",ex);
    	resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex.getMessage());

    }
    
    @ExceptionHandler(HttpCodeException.class)
    @ResponseBody
    public ErrorResponse  handleHttpError(HttpServletRequest req, HttpServletResponse resp , HttpCodeException ex) throws IOException {
    	loggerException.error("Request: [{}] message: [{}]" , req.getRequestURL() , ex.getMessage());
    	resp.sendError(ex.getStatusCode().value(),ex.getMessage());
    	
    	ErrorResponse error = new ErrorResponse();
    	
    	error.setTimestamp( String.valueOf( (new Date()).getTime() ));
    	error.setStatus(String.valueOf(ex.getStatusCode().value()));
    	error.setError(ex.getStatusCode().name());
    	error.setException(HttpCodeException.class.getName());
    	error.setMessage(ex.getMessage());
    	error.setPath(req.getRequestURL().toString());
    	error.setMessages(ex.getMessages());
    	
    	return error;

    }
    
}
