package com.mk.mnx.mdc.paciente.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mk.mnx.mdc.model.domain.Paciente;

public interface PacienteRepository extends MongoRepository<Paciente, String> {
	
	@Query("{contacto.nombreCompleto : {$regex: ?0 } }")
	List<Paciente> findLikeNombre(String nombre);

}
