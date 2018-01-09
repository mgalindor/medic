package com.mk.mnx.infr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mk.mnx.infr.constants.CommonConstants;
import com.mk.mnx.infr.exception.HttpCodeException;

public abstract class BaseRestController {
	
    protected final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGGER_COMTROLLER);

    protected final Logger loggerException = LoggerFactory.getLogger(CommonConstants.LOGGER_EXCEPTION);
    
    @Autowired
	private HttpServletRequest request;
   
    private String user;
    
    protected String getUser() {	
		//return (String) request.getAttribute(CommonConstants.SESSION_USER);
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
    public void handleHttpError(HttpServletRequest req, HttpServletResponse resp , HttpCodeException ex) throws IOException {
    	loggerException.error("Request: [{}] message: [{}]" , req.getRequestURL() , ex.getMessage());
    	loggerException.error("HTTP Error:",ex);
    	resp.sendError(ex.getHttpCode(),ex.getMessage());

    }
    
}
