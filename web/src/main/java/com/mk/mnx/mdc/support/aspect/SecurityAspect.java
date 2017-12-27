package com.mk.mnx.mdc.support.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mk.mnx.infr.constants.CommonConstants;
import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.support.annotation.AccessValidation;

import io.jsonwebtoken.Jwts;

@Aspect
@Component
public class SecurityAspect {

	protected final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGGER_INFRA);

	@SuppressWarnings("unchecked")
	@Around("execution(public * *(..)) && this(com.mk.mnx.infr.controller.BaseRestController) "   
			+ "&& @target(org.springframework.web.bind.annotation.RestController) && @annotation(com.mk.mnx.mdc.support.annotation.AccessValidation)")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("Acces validation");
		BaseRestController ctl = (BaseRestController) joinPoint.getTarget();		
		Class<? extends Object> c = joinPoint.getTarget().getClass();
		Method m = c.getMethod(joinPoint.getSignature().getName());
		AccessValidation va = m.getAnnotation(AccessValidation.class);

		//Realiza la validacion del token y roles
		if(va.enabled()) {
			HttpServletRequest httpRequest = ctl.getRequest();
			String tokem = httpRequest.getHeader(CommonConstants.SESSION_HTTP_HEADER);
			if (tokem == null) {
				throw new SecurityException("Autentication is necesary");
			} else {
				try {
					String user = Jwts.parser().setSigningKey(CommonConstants.TOKEN_PASS).parseClaimsJws(tokem).getBody()
							.getSubject();
					List<EnuRole> roles = (List<EnuRole>) Jwts.parser().setSigningKey(CommonConstants.TOKEN_PASS).parseClaimsJws(tokem).getBody().get(CommonConstants.TOKEN_ROLES);
					
					if  (!CollectionUtils.isSubCollection(Arrays.asList(va.roles()), roles))
					{
						logger.error("Roles requerios [{}]", Arrays.asList(va.roles()));
						logger.error("Roles token [{}]", roles );
						throw new SecurityException("Autentication is not valid");
					}
					
					logger.debug("User [{}]", user);
					httpRequest.setAttribute(CommonConstants.SESSION_USER, user);
					return joinPoint.proceed();
					
					
				} catch (Exception e) {
					logger.error("Error al validar el token ", e);
					throw new SecurityException("Autentication is not valid");
				}
			}
		}
		else {
			return joinPoint.proceed();
		}
	}

}
