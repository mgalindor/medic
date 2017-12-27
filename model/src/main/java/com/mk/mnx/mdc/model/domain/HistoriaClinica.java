package com.mk.mnx.mdc.model.domain;

import com.mk.mnx.infr.model.BaseModel;

public class HistoriaClinica extends BaseModel{
	
	private FichaIdentificacion fichaIdentificacion;

	private String antecedentesPatologicos;
	
	private AntecedentesGinecoObstetra antecedentesGinecoObstetra;
	
	private String antecedentesHeredofamiliares;
	
	private String antecedentesNoPatologicos;
	
	private InterrogatorioApartadoSistemas interrogatorioApartadoSistemas;
	
//	private SignosVitales signosVitales;
//	
//	private Somatometria somatometria;
//	
//	private ExploracionFisica exploracionFisica;
//	
//	private String diagnostico;
//	
//	private String tratamiento;
//	
//	private String notas;

	public FichaIdentificacion getFichaIdentificacion() {
		return fichaIdentificacion;
	}

	public void setFichaIdentificacion(FichaIdentificacion fichaIdentificacion) {
		this.fichaIdentificacion = fichaIdentificacion;
	}

	public String getAntecedentesPatologicos() {
		return antecedentesPatologicos;
	}

	public void setAntecedentesPatologicos(String antecedentesPatologicos) {
		this.antecedentesPatologicos = antecedentesPatologicos;
	}

	public AntecedentesGinecoObstetra getAntecedentesGinecoObstetra() {
		return antecedentesGinecoObstetra;
	}

	public void setAntecedentesGinecoObstetra(AntecedentesGinecoObstetra antecedentesGinecoObstetra) {
		this.antecedentesGinecoObstetra = antecedentesGinecoObstetra;
	}

	public String getAntecedentesHeredofamiliares() {
		return antecedentesHeredofamiliares;
	}

	public void setAntecedentesHeredofamiliares(String antecedentesHeredofamiliares) {
		this.antecedentesHeredofamiliares = antecedentesHeredofamiliares;
	}

	public String getAntecedentesNoPatologicos() {
		return antecedentesNoPatologicos;
	}

	public void setAntecedentesNoPatologicos(String antecedentesNoPatologicos) {
		this.antecedentesNoPatologicos = antecedentesNoPatologicos;
	}

	public InterrogatorioApartadoSistemas getInterrogatorioApartadoSistemas() {
		return interrogatorioApartadoSistemas;
	}

	public void setInterrogatorioApartadoSistemas(InterrogatorioApartadoSistemas interrogatorioApartadoSistemas) {
		this.interrogatorioApartadoSistemas = interrogatorioApartadoSistemas;
	}
	
}
