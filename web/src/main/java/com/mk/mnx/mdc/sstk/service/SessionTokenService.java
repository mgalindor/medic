package com.mk.mnx.mdc.sstk.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.constants.CommonConstants;
import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.sstk.model.TokenResponse;
import com.mk.mnx.mdc.sstk.repository.UserRepository;
import com.mk.mnx.mdc.support.helper.BeanHelper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SessionTokenService extends BaseService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BeanHelper beanHelper; 

	public TokenResponse creaSessionToken(String userName, String password) throws HttpCodeException {
		logger.debug("USer [{}] passs [{}]",userName,password);
		TokenResponse response = null;
		Usuario u = userRepository.findUserByUserName(userName);
		if (u != null) {
			if (beanHelper.matchesPassword(u.getPassword() ,  password)) {
				response = new TokenResponse();
				response.setUsuario(u);
				response.setToken(generateToken (u));
				response.setRefreshToken(generateRefreshToken(u));
			}
			else {
				throw new HttpCodeException(HttpStatus.BAD_REQUEST);
			}
		}else {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	
	public TokenResponse creaRefreshToken( String refreshHeader ) throws HttpCodeException {
		TokenResponse response = null;
		String refreshToken = refreshHeader.replaceFirst(CommonConstants.SESSION_HTTP_HEADER_PREFIX, "");
		try {
			Claims c = Jwts.parser()
			  .setSigningKey(CommonConstants.TOKEN_PASS)
			  .parseClaimsJws(refreshToken).getBody();
			
			String user = c.getSubject();
			Set<EnuRole> roles = (Set<EnuRole>) c.get(CommonConstants.TOKEN_ROLES);
			

			String tokenType =  (String) c.get(CommonConstants.TOKEN_TYPE);
			if(tokenType.equals(CommonConstants.TOKEN_TYPE_REF)) {
				throw new HttpCodeException(HttpStatus.FORBIDDEN);
			}
			
			Usuario u = new Usuario();
			u.setNombre(user);
			u.setRoles(roles);
		
			response = new TokenResponse();
			response.setUsuario(u);
			response.setToken(generateToken (u));
			response.setRefreshToken(generateRefreshToken(u));
		}catch(ExpiredJwtException e) {
			logger.error("Error",e);
			throw new HttpCodeException(HttpStatus.FORBIDDEN);
		}catch(Exception e) {
			logger.error("Error",e);
			throw new HttpCodeException(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	private Date generateExpiration(int expiration) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, expiration );
		return cal.getTime();
	}
	
	private String generateTokenJWT(String userName, Set<EnuRole> roles) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put(CommonConstants.TOKEN_ROLES, roles);
		m.put(CommonConstants.TOKEN_TYPE, CommonConstants.TOKEN_TYPE_GRANT);
		String JWT = Jwts.builder().setSubject(userName).setClaims(m)
				.setExpiration(generateExpiration(CommonConstants.NUMBER_OF_DAYS_TOKEN_LIVE))
				.signWith(SignatureAlgorithm.HS512, CommonConstants.TOKEN_PASS).compact();
		return JWT;
	}
	
	private String generateToken(Usuario u) {
		String t = generateTokenJWT(u.getNombre(),u.getRoles());
		return t;
	}
	
	private String generateRefreshToken(Usuario u) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put(CommonConstants.TOKEN_ROLES, u.getRoles());
		m.put(CommonConstants.TOKEN_TYPE, CommonConstants.TOKEN_TYPE_REF);		
		String JWT = Jwts.builder().setSubject(u.getNombre()).setClaims(m)
				.setExpiration(generateExpiration(CommonConstants.NUMBER_OF_DAYS_TOKEN_RF_LIVE))
				.signWith(SignatureAlgorithm.HS512, CommonConstants.TOKEN_PASS).compact();
		return JWT;
	}

}
