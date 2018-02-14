package com.mk.mnx.mdc.paciente.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.states.EnuSexo;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;
import com.mk.mnx.vld.annotation.Validate;

public interface PacienteService {

	@Validate
    @Rules( {@Rule(parameter="paciente",path="contacto.nombre" ,required=true ),
    		@Rule(parameter="paciente",path="contacto.apPaterno" ,required=true ),
    		@Rule(parameter="paciente",path="contacto.apMaterno" ,required=true ),
		    @Rule(parameter="paciente",path="contacto.tel" ,required=true ),
			@Rule(parameter="paciente",path="fecNacimiento" ,required=true ),
			@Rule(parameter="paciente",path="sexo" ,required=true ),
			@Rule(parameter="paciente",path="curp" ,required=true )
    }) 
	Paciente creaPaciente(Paciente paciente, String currentUser);

	@Validate
    @Rules( {@Rule(parameter="paciente",path="id" ,required=true ),
    		@Rule(parameter="paciente",path="contacto.nombre" ,required=true ),
    		@Rule(parameter="paciente",path="contacto.apPaterno" ,required=true ),
    		@Rule(parameter="paciente",path="contacto.apMaterno" ,required=true ),
		    @Rule(parameter="paciente",path="contacto.tel" ,required=true ),
			@Rule(parameter="paciente",path="fecNacimiento" ,required=true ),
			@Rule(parameter="paciente",path="sexo" ,required=true ),
			@Rule(parameter="paciente",path="curp" ,required=true )
    }) 
	Paciente actualizaPaciente(Paciente paciente, String currentUser);

	@Validate
    @Rules( @Rule(parameter="paciente",path="id" ,required=true ) ) 
	Paciente borraPaciente(Paciente paciente, String currentUser);

	@Validate
    @Rules( @Rule(parameter="idPaciente" ,required=true ) ) 
	Paciente buscaPaciente(String idPaciente);

	List<Paciente> buscaPacientes(String name, String curp, EnuSexo sexo, String createdBy, Boolean active,
			Direction sort, Integer page, Integer results);

	Long buscaTotalPacientes(String name, String curp, EnuSexo sexo, String createdBy, Boolean active);

}
