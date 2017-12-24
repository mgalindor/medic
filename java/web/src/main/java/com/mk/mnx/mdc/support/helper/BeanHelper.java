package com.mk.mnx.mdc.support.helper;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Especializacion de org.apache.commons.beanutils.BeanUtilsBean
 * cuenta con la particularida que al copiar propiedades ignora
 * las que sean nulas. 
 * 
 * @author MK
 */
@Component
public class BeanHelper extends BeanUtilsBean {

	
	BCryptPasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value!=null) {
        	super.copyProperty(dest, name, value);
        }
    }
	
	public String encode (String in) {
		return passwordEncoder.encode(in);
	}
	
	public boolean matchesPassword(String encodedPassword , String clair) {
		return passwordEncoder.matches(clair, encodedPassword);
	}
	
	
}
