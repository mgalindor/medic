package com.mk.mnx.mdc.paciente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mk.mnx.mdc.model.domain.Paciente;

public interface PacienteRepository extends MongoRepository<Paciente, String> {
	
	Paciente findByCurp(String curp);
	
}
