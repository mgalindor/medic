package com.mk.mnx.mdc.model.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctores")
public class Doctor {
	
	@Id
	private String id;
	
	private String idUsuario;
	
	private Contacto contacto;
	
	private String cedula;
	
	private String titulo;
	
	private String resumen;
	
	private Date creacion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public Date getCreacion() {
		return creacion;
	}
	
	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

}
