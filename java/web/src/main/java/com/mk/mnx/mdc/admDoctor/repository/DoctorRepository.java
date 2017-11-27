package com.mk.mnx.mdc.admDoctor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mk.mnx.mdc.model.domain.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String>{

	@Query("{idUsuario :'?0'}")
	Doctor findByIdUsuario(String idUsuario);
}
