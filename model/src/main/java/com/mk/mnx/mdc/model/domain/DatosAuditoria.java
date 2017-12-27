package com.mk.mnx.mdc.model.domain;

import java.util.Date;
import java.util.List;

public class DatosAuditoria {
	
	private String idUsuarioCreacion;
	
	private Date creacion;
	
	private boolean enable;
	
	private List<FootPrint> modificado;

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<FootPrint> getModificado() {
		return modificado;
	}

	public void setModificado(List<FootPrint> modificado) {
		this.modificado = modificado;
	}

	public String getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(String idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}
	
}
