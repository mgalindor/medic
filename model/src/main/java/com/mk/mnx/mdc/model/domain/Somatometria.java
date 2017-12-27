package com.mk.mnx.mdc.model.domain;

import com.mk.mnx.infr.model.BaseModel;

public class Somatometria extends BaseModel {

	private Float talla;		
	private Float peso;
	private Float cintura;		
	private Float cadera;
	public Float getTalla() {
		return talla;
	}
	public void setTalla(Float talla) {
		this.talla = talla;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Float getCintura() {
		return cintura;
	}
	public void setCintura(Float cintura) {
		this.cintura = cintura;
	}
	public Float getCadera() {
		return cadera;
	}
	public void setCadera(Float cadera) {
		this.cadera = cadera;
	}		

	
	
}
