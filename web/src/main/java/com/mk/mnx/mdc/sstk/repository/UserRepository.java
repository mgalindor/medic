package com.mk.mnx.mdc.sstk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mk.mnx.mdc.model.domain.Usuario;

public interface UserRepository extends MongoRepository<Usuario, String> {
	
	 @Query("{userName:'?0'}")
	 Usuario findUserByUserName(String userName);

}
