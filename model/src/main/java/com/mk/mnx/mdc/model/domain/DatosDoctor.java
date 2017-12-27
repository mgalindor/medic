package com.mk.mnx.mdc.model.domain;

import java.util.Date;

import com.mk.mnx.infr.model.BaseModel;

public class DatosDoctor extends BaseModel {

	private Contacto contacto;
	
	private String cedula;
	
	private String especialidad;
	
	private Date creacion;
	
	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

}
