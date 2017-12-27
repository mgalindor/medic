package com.mk.mnx.mdc.sstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.sstk.model.TokenResponse;
import com.mk.mnx.mdc.sstk.service.SessionTokenService;

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
	public TokenResponse refreshToken(@RequestBody String  refreshToken) throws HttpCodeException {
		TokenResponse r = sessionTokenService.creaRefreshToken(refreshToken);
		return r;
	}

}
