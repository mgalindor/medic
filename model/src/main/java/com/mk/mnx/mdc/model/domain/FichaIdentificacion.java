package com.mk.mnx.mdc.model.domain;

import com.mk.mnx.infr.model.BaseModel;

public class FichaIdentificacion extends BaseModel {
	
	private String hemotipo;
	private String edoCivil;
	private String escolaridad;
	private String ocupacion;
	private String lugatOrigen;
	private String lugarResidencia;
	private String religion;
	
	public String getHemotipo() {
		return hemotipo;
	}
	public void setHemotipo(String hemotipo) {
		this.hemotipo = hemotipo;
	}
	public String getEdoCivil() {
		return edoCivil;
	}
	public void setEdoCivil(String edoCivil) {
		this.edoCivil = edoCivil;
	}
	public String getEscolaridad() {
		return escolaridad;
	}
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getLugatOrigen() {
		return lugatOrigen;
	}
	public void setLugatOrigen(String lugatOrigen) {
		this.lugatOrigen = lugatOrigen;
	}
	public String getLugarResidencia() {
		return lugarResidencia;
	}
	public void setLugarResidencia(String lugarResidencia) {
		this.lugarResidencia = lugarResidencia;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}

	
	
}
