package com.mk.mnx.mdc.paciente.repository;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.states.EnuSexo;

public interface PacienteCustomRepository {

	List<Paciente> buscarPasientes(String nombre, String curp, EnuSexo sexo, Boolean status, String createdBy,
			Direction sort, Integer page, Integer results);

	Long buscarTotalPasientes(String nombre, String curp, EnuSexo sexo, Boolean status, String createdBy);
	
}
