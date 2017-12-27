package com.mk.mnx.infr.constants;

public interface CommonConstants {
	
	String SESSION_USER="session_user";
	String SESSION_HTTP_HEADER = "Session-Token";
	String MDC_HTTP_HEADER = "smdcTkn";
	String MDC_TOKEN = "stkn";
	
	String TOKEN_PASS="mnk$123";
	Integer NUMBER_OF_DAYS_TOKEN_LIVE=1;
	Integer NUMBER_OF_DAYS_TOKEN_RF_LIVE=2;
	String TOKEN_ROLES="roles";
	
	String TOKEN_TYPE = "ttyp";
	String TOKEN_TYPE_GRANT = "grant";
	String TOKEN_TYPE_REF = "ref";
	
	
	
	
	String LOGGER_INFRA="infra";
	String LOGGER_COMTROLLER="rest";
	String LOGGER_EXCEPTION="exception";
			

}
