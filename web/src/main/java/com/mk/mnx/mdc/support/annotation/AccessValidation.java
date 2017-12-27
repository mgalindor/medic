package com.mk.mnx.mdc.support.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.mk.mnx.mdc.model.states.EnuRole;

@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessValidation {

	public boolean enabled() default true;
	
	/**
	 * Roles que debe tener el usuario para acceder
	 * @return
	 */
	EnuRole[] roles() default {EnuRole.USER};
	
}
