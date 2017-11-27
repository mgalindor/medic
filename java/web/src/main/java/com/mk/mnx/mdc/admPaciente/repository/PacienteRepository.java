package com.mk.mnx.mdc.admPaciente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mk.mnx.mdc.model.domain.Paciente;

public interface PacienteRepository extends MongoRepository<Paciente, String> {

}
