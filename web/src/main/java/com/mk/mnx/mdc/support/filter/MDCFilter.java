package com.mk.mnx.mdc.support.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.mk.mnx.infr.constants.CommonConstants;

@Component
public class MDCFilter implements Filter {
	
	protected final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGGER_INFRA);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String mdcData = httpRequest.getHeader(CommonConstants.MDC_HTTP_HEADER);
			if(mdcData == null)
			{
				Random rnd = new Random();
				mdcData = String.format("%1$04d-%2$d", (rnd.nextInt(1000) ), new Date().getTime());
			}
			MDC.put(CommonConstants.MDC_TOKEN, mdcData);
			logger.info("Estoy en el filtro [{}]",mdcData);
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.addHeader(CommonConstants.MDC_HTTP_HEADER, mdcData);
			chain.doFilter(request, response);
		} finally {
			MDC.clear();
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

}
