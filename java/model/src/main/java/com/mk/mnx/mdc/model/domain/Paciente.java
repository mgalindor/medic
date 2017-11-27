package com.mk.mnx.mdc.model.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mk.mnx.infr.model.BaseModel;

@Document(collection = "pacientes")
public class Paciente extends BaseModel{
	
	@Id
	private String id;
	
	private Contacto contacto;
	
	private Date fecNacimiento;
	
	private Date creacion;
	
	private String idDoctorCreacion;
	
	public Contacto getContacto() {
		return contacto;
	}
	
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public String getIdDoctorCreacion() {
		return idDoctorCreacion;
	}

	public void setIdDoctorCreacion(String idDoctorCreacion) {
		this.idDoctorCreacion = idDoctorCreacion;
	}
	
	public Date getFecNacimiento() {
		return fecNacimiento;
	}
	
	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}
	

}
