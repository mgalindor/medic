package com.mk.mnx.mdc.support.helper;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
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

	@Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value!=null) {
        	super.copyProperty(dest, name, value);
        }
    }
	
}
