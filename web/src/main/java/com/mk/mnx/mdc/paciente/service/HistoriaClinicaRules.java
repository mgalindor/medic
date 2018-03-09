package com.mk.mnx.mdc.paciente.service;

import com.mk.mnx.vld.annotation.ExternalRule;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;

public interface HistoriaClinicaRules {

	@Rules({ @Rule(parameter = "historia", path = "fichaIdentificacion", required = true),
			@Rule(parameter = "historia", path = "fichaIdentificacion.hemotipo", required = true),
			@Rule(parameter = "historia", path = "fichaIdentificacion.edoCivil", required = true),
			@Rule(parameter = "historia", path = "fichaIdentificacion.escolaridad", required = true),
			@Rule(parameter = "historia", path = "fichaIdentificacion.ocupacion", required = true),
			@Rule(parameter = "historia", path = "fichaIdentificacion.lugarOrigen", required = true),
			@Rule(parameter = "historia", path = "fichaIdentificacion.lugarResidencia", required = true),
			@Rule(parameter = "historia", path = "fichaIdentificacion.religion", required = true), })
	public void fichaIdentificacion();

	@Rules({ @Rule(parameter = "historia", path = "interrogatorioApartadoSistemas", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.nervioso", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.respiratorio", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.cardiovascular", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.digestivo", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.urinario", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.aparatoGenital", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.endocrino", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.hematopoyetico", required = true),
			@Rule(parameter = "historia", path = "interrogatorioApartadoSistemas.musculoEsqueletico", required = true), })
	public void interrogatorioApartadoSistemas();

	@Rules(
			value = { 	@Rule(parameter = "historia", path = "antecedentesPatologicos", required = true),
						@Rule(parameter = "historia", path = "antecedentesHeredofamiliares", required = true),
						@Rule(parameter = "historia", path = "antecedentesNoPatologicos", required = true), }, 
			externalRules = {
						@ExternalRule(classRule = HistoriaClinicaRules.class, mehtodRule = "fichaIdentificacion"),
						@ExternalRule(classRule = HistoriaClinicaRules.class, mehtodRule = "interrogatorioApartadoSistemas") }
			)
	public void historia();
}
