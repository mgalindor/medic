package com.mk.mnx.mdc.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatosAuditoria {
	
	private String idUsuarioCreacion;
	
	private Date creacion;
	
	private boolean active;
	
	private List<FootPrint> modificado;

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}

	public List<FootPrint> getModificado() {
		return modificado;
	}

	public void setModificado(List<FootPrint> modificado) {
		this.modificado = modificado;
	}
	
	public void addModificado(FootPrint m) {
		if(modificado == null) {
			modificado = new ArrayList<FootPrint>();
		}
		modificado.add(m);
	}
	
	public FootPrint getModificado(int index) {
		return modificado.get(index);
	}

	public String getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(String idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}
	
}
