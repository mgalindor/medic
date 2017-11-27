package com.mk.mnx.infr.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mk.mnx.infr.constants.CommonConstants;

public abstract class BaseService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpServletRequest request;

	protected String getUser() {
		HttpSession session = request.getSession(true);
		return (String) session.getAttribute(CommonConstants.SESSION_USER);
	}
}
