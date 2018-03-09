package com.mk.mnx.mdc.paciente.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.mk.mnx.mdc.model.domain.HistoriaClinica;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.states.EnuSexo;
import com.mk.mnx.vld.annotation.ExternalRule;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;

public interface PacienteService {

	@Rules( {@Rule(parameter="paciente",path="contacto.nombre" ,required=true ),
		@Rule(parameter="paciente",path="contacto.apPaterno" ,required=true ),
		@Rule(parameter="paciente",path="contacto.apMaterno" ,required=true ),
	    @Rule(parameter="paciente",path="contacto.tel" ,required=true ),
		@Rule(parameter="paciente",path="fecNacimiento" ,required=true ),
		@Rule(parameter="paciente",path="sexo" ,required=true ),
		@Rule(parameter="paciente",path="curp" ,required=true )
	}) 
	Paciente creaPaciente(Paciente paciente, String currentUser);

    @Rules( value= @Rule(parameter="paciente",path="id" ,required=true )
    		, externalRules =@ExternalRule(classRule=PacienteService.class,mehtodRule="creaPaciente")) 
	Paciente actualizaPaciente(Paciente paciente, String currentUser);

    @Rules( @Rule(parameter="paciente",path="id" ,required=true ) ) 
	Paciente borraPaciente(Paciente paciente, String currentUser);

    @Rules( @Rule(parameter="idPaciente" ,required=true ) ) 
	Paciente buscaPaciente(String idPaciente);

	List<Paciente> buscaPacientes(String name, String curp, EnuSexo sexo, String createdBy, Boolean active,
			Direction sort, Integer page, Integer results);

	Long buscaTotalPacientes(String name, String curp, EnuSexo sexo, String createdBy, Boolean active);

	@Rules(  externalRules= {@ExternalRule(classRule=HistoriaClinicaRules.class,mehtodRule="historia")})
	HistoriaClinica creaHistoria(HistoriaClinica historia, String idPaciente, String currentUser);

	@Rules( value= @Rule(parameter="historia" ,required=true), 
			externalRules= {@ExternalRule(classRule=HistoriaClinicaRules.class,mehtodRule="historia")})
	HistoriaClinica actualizaHistoria(HistoriaClinica historia, String idPaciente, String currentUser);

	HistoriaClinica buscaHistoria(String idPaciente);

}
