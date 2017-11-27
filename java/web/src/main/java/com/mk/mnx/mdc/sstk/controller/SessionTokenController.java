package com.mk.mnx.mdc.sstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.sstk.service.SessionTokenService;

@RestController
public class SessionTokenController extends BaseRestController {
	
	@Autowired 
	SessionTokenService sessionTokenService;
	
	@PostMapping("sessionToken")
	public String createSessionToken(@RequestBody Usuario in) {
		String r = "";
		try {
		logger.debug("Entrada [{}]",in);
			r= sessionTokenService.creaSessionToken(in.getNombre(), in.getPassword());
		}catch(Exception e) {
			logger.error("Error ",e);
			r ="error";
		}
		return r;
	}

	@GetMapping("hola")
	public String hola() {return "hola";}
}
