package com.mk.mnx.mdc.admDoctor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mk.mnx.mdc.model.domain.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	@Query("{nombre :'?0'}")
	Usuario findByNombre(String nombre);
}

