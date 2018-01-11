package com.mk.mnx.mdc.support.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mk.mnx.infr.constants.CommonConstants;
import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.mdc.config.MnxProperties;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.support.annotation.AccessValidation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Aspect
@Component
public class SecurityAspect {

	protected final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGGER_INFRA);

	@Autowired
	private MnxProperties mnxProperties;
	
	@SuppressWarnings("unchecked")
	@Around("execution(public * *(..)) && this(com.mk.mnx.infr.controller.BaseRestController) "   
			+ "&& @target(org.springframework.web.bind.annotation.RestController) && @annotation(com.mk.mnx.mdc.support.annotation.AccessValidation)")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable    {
		logger.debug("Acces validation");
		BaseRestController ctl = (BaseRestController) joinPoint.getTarget();		
		//Class<? extends Object> c = joinPoint.getTarget().getClass();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        Method m = methodSignature.getMethod();
		AccessValidation va = m.getAnnotation(AccessValidation.class);

		//Realiza la validacion del token y roles
		if(va.enabled()  && mnxProperties.isEnabled() ) {
			HttpServletRequest httpRequest = ctl.getRequest();
			String header = httpRequest.getHeader(CommonConstants.SESSION_HTTP_HEADER);
			if (header == null) {
				throw new HttpCodeException(HttpServletResponse.SC_UNAUTHORIZED,"Autentication is necesary");
			} else {
				try {
					String token = header.replaceFirst(CommonConstants.SESSION_HTTP_HEADER_PREFIX, "");
					Claims claimToken = Jwts.parser().setSigningKey(CommonConstants.TOKEN_PASS).parseClaimsJws(token).getBody();
					String user = claimToken.getSubject();
					List<EnuRole> roles = (List<EnuRole>) claimToken.get(CommonConstants.TOKEN_ROLES);
					
					if  (!CollectionUtils.isSubCollection(Arrays.asList(va.roles()), roles))
					{
						logger.error("Roles requerios [{}]", Arrays.asList(va.roles()));
						logger.error("Roles token [{}]", roles );
						throw new SecurityException("Autentication is not valid");
					}
					
					logger.debug("User [{}]", user);
					//httpRequest.setAttribute(CommonConstants.SESSION_USER, user);
					FieldUtils.writeField(ctl, "user", user, true);
					return joinPoint.proceed();
				} catch (Exception e) {
					logger.error("Error al validar el token ", e);
					throw new HttpCodeException(HttpServletResponse.SC_FORBIDDEN,"Autentication is not valid");
				}
			}
		}
		else {
			return joinPoint.proceed();
		}
	}

}
