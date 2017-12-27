package com.mk.mnx.mdc.sstk.model;

import com.mk.mnx.infr.model.BaseModel;
import com.mk.mnx.mdc.model.domain.Usuario;

public class TokenResponse extends BaseModel {
	
	private Usuario usuario;
	
	private String token;
	
	private String refreshToken ;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
}
