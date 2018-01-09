package com.mk.mnx.mdc.sstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.constants.CommonConstants;
import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.sstk.model.TokenResponse;
import com.mk.mnx.mdc.sstk.service.SessionTokenService;
import com.mk.mnx.mdc.support.annotation.AccessValidation;

@RestController
@RequestMapping("session")
public class SessionTokenController extends BaseRestController {
	
	@Autowired 
	SessionTokenService sessionTokenService;
	
	@PostMapping("token")
	public TokenResponse createToken(@RequestBody Usuario in) throws HttpCodeException {
		TokenResponse r = sessionTokenService.creaSessionToken(in.getNombre(), in.getPassword());
		return r;
	}
	
	@PostMapping("refresh")
	@AccessValidation(roles= {EnuRole.USER})
	public TokenResponse refreshToken(@RequestHeader(CommonConstants.SESSION_HTTP_REFRESH_HEADER) String  refreshHeader) throws HttpCodeException {
		TokenResponse r = sessionTokenService.creaRefreshToken(refreshHeader);
		return r;
	}

}
