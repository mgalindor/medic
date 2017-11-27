package com.mk.mnx.mdc.sstk.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mk.mnx.infr.constants.CommonConstants;
import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.MDCRole;
import com.mk.mnx.mdc.sstk.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SessionTokenService extends BaseService {

	@Autowired
	private UserRepository userRepository;

	public String creaSessionToken(String userName, String password) {
		logger.debug("USer [{}] passs [{}]",userName,password);
		Usuario u = userRepository.findUserByUserName(userName);
		String token="";
		if (u != null) {
			String pass= DigestUtils.md5DigestAsHex(password.getBytes());
			
			logger.debug("md5 {}",pass);
			
			if (u.getPassword().equals(pass)) {
				if(u.getToken() == null) {
					String t = generateToken(u);
					token = t;
				}
				
				else if(u.getToken() != null) {
					try {
						Jwts.parser()
						  .setSigningKey(CommonConstants.TOKEN_PASS)
						  .parseClaimsJws(u.getToken()).getBody().getSubject();
						token = u.getToken();
					}
					catch (ExpiredJwtException e) {
						String t = generateToken(u);
						token = t;
					}catch(Exception e) {
						logger.error("Error al validar token",e);
					}
				}
			}
		}
		return token;
	}
	
	private Date generateExpiration() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, CommonConstants.NUMBER_OF_DAYS_TOKEN_LIVE);
		return cal.getTime();
	}
	
	private String generateTokenJWT(String userName, List<MDCRole> roles) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put(CommonConstants.TOKEN_ROLES, roles);
		String JWT = Jwts.builder().setSubject(userName).setClaims(m)
				.setExpiration(generateExpiration())
				.signWith(SignatureAlgorithm.HS512, CommonConstants.TOKEN_PASS).compact();
		return JWT;
	}
	
	private String generateToken(Usuario u) {
		String t = generateTokenJWT(u.getNombre(),u.getRoles());
		u.setToken(t);
		userRepository.save(u);
		return t;
	}
	

}
