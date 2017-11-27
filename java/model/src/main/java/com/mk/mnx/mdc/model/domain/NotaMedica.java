package com.mk.mnx.mdc.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mk.mnx.infr.model.BaseModel;

@Document(collection = "notasMedicas")
public class NotaMedica extends BaseModel {

	@Id
	private String id;
	
	@Indexed
	private String idPaciente;
	
	@Indexed
	private String idDoctor;
	
	@Transient
	private Paciente paciente;
	
	@Transient
	private Doctor doctor;
	
	private String medHigieneDieta;
	
	private String antecedentesHereditarios;
	
	private String estudiosLaboratorio;
	
	private String antecedentesPersonalesPat;
	
	private String antecedentesPersonalesNoPat;
	
	private String ocupacion;
	
	private String alergias;
	
	private String tipoSangre;
	
	private String sexo;
	
	private String altura;
	
	private String peso;
	
	private String imc;
	
	private String presionArt;
	
	private String temperatura;
	
	private String frecResp;
	
	private String frecCard;
	
	private String padecimientoActual;
	
	//enfermedades cronicas degenerativas
	private String ecd;
	
	private String exploracionFisica;
	
	private String antecedentesGinecoObstetra;
	
	private String saturacionOxigeno;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedHigieneDieta() {
		return medHigieneDieta;
	}

	public void setMedHigieneDieta(String medHigieneDieta) {
		this.medHigieneDieta = medHigieneDieta;
	}

	public String getAntecedentesHereditarios() {
		return antecedentesHereditarios;
	}

	public void setAntecedentesHereditarios(String antecedentesHereditarios) {
		this.antecedentesHereditarios = antecedentesHereditarios;
	}

	public String getEstudiosLaboratorio() {
		return estudiosLaboratorio;
	}

	public void setEstudiosLaboratorio(String estudiosLaboratorio) {
		this.estudiosLaboratorio = estudiosLaboratorio;
	}

	public String getAntecedentesPersonalesPat() {
		return antecedentesPersonalesPat;
	}

	public void setAntecedentesPersonalesPat(String antecedentesPersonalesPat) {
		this.antecedentesPersonalesPat = antecedentesPersonalesPat;
	}

	public String getAntecedentesPersonalesNoPat() {
		return antecedentesPersonalesNoPat;
	}

	public void setAntecedentesPersonalesNoPat(String antecedentesPersonalesNoPat) {
		this.antecedentesPersonalesNoPat = antecedentesPersonalesNoPat;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public String getPresionArt() {
		return presionArt;
	}

	public void setPresionArt(String presionArt) {
		this.presionArt = presionArt;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getFrecResp() {
		return frecResp;
	}

	public void setFrecResp(String frecResp) {
		this.frecResp = frecResp;
	}

	public String getFrecCard() {
		return frecCard;
	}

	public void setFrecCard(String frecCard) {
		this.frecCard = frecCard;
	}

	public String getPadecimientoActual() {
		return padecimientoActual;
	}

	public void setPadecimientoActual(String padecimientoActual) {
		this.padecimientoActual = padecimientoActual;
	}

	public String getEcd() {
		return ecd;
	}

	public void setEcd(String ecd) {
		this.ecd = ecd;
	}

	public String getExploracionFisica() {
		return exploracionFisica;
	}

	public void setExploracionFisica(String exploracionFisica) {
		this.exploracionFisica = exploracionFisica;
	}

	public String getAntecedentesGinecoObstetra() {
		return antecedentesGinecoObstetra;
	}

	public void setAntecedentesGinecoObstetra(String antecedentesGinecoObstetra) {
		this.antecedentesGinecoObstetra = antecedentesGinecoObstetra;
	}

	public String getSaturacionOxigeno() {
		return saturacionOxigeno;
	}

	public void setSaturacionOxigeno(String saturacionOxigeno) {
		this.saturacionOxigeno = saturacionOxigeno;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	
}
