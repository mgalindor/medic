package com.mk.mnx.mdc.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.domain.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
	@Query("{nombre :'?0'}")
	Usuario findByNombre(String nombre);
	
	@Query("{nombre : {$regex: ?0 }}")
	List<Usuario> findLikeNombre(String nombre);

	@Query("{datosDoctor.contacto.nombreCompleto : {$regex: ?0 } , roles : { $in : ['MEDICO'] } } ")
	List<Paciente> findLikeDoctorNombre(String nombre);
}
