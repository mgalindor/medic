package com.mk.mnx.mdc.notas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mk.mnx.mdc.model.domain.Nota;

public interface NotaRepository extends MongoRepository<Nota , String>  {
	
}
