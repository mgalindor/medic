package com.mk.mnx.infr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mk.mnx.infr.constants.CommonConstants;

public abstract class BaseRestController {
	
    protected final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGGER_COMTROLLER);

    protected final Logger loggerException = LoggerFactory.getLogger(CommonConstants.LOGGER_EXCEPTION);
    
    @Autowired
	private HttpServletRequest request;
   
    protected String getUser() {	
		return (String) request.getAttribute(CommonConstants.SESSION_USER);
	}
    
    @ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest req, HttpServletResponse resp , Exception ex) throws IOException {
    	//loggerException.error("Request: [{}] message: [{}]" , req.getRequestURL() , ex.getMessage());
    	loggerException.error("Error:",ex);
    	resp.sendError(HttpServletResponse.SC_CONFLICT,ex.getMessage());

    }

    public HttpServletRequest getRequest() {
		return request;
	}
    
}
