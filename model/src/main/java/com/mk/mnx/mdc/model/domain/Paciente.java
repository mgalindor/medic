package com.mk.mnx.mdc.model.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mk.mnx.infr.model.BaseModel;
import com.mk.mnx.mdc.model.states.EnuSexo;

@Document(collection = "pacientes")
public class Paciente extends BaseModel{
	
	@Id
	private String id;
	
	private Contacto contacto;
	
	private Date fecNacimiento;
	
	private String diagnostico;
	
	private EnuSexo sexo;
	
	@Indexed(unique=true)
	private String curp;
	
	private HistoriaClinica historiaClinica;
	
	private DatosAuditoria datosAuditoria;

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

	public Date getFecNacimiento() {
		return fecNacimiento;
	}

	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public EnuSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnuSexo sexo) {
		this.sexo = sexo;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public DatosAuditoria getDatosAuditoria() {
		return datosAuditoria;
	}

	public void setDatosAuditoria(DatosAuditoria datosAuditoria) {
		this.datosAuditoria = datosAuditoria;
	}
	
	public String getCurp() {
		return curp;
	}
	
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
}
