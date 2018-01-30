package com.mk.mnx.mdc.user.repository.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mk.mnx.infr.repository.BaseCustomRepository;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.user.repository.UserCustomRepository;

@Repository
public class UserCustomRepositoryImpl extends BaseCustomRepository implements UserCustomRepository {

	public List<Usuario> buscarUsuarios(String name, String email, Boolean status, Direction sort, Integer page,
			Integer results , String cedula , String role , boolean datosDoctorLlenos) {

		Query query = new Query();
		if (email != null) {
			query.addCriteria(Criteria.where("nombre").regex(email));
		}
		if (name != null) {
			query.addCriteria(Criteria.where("datosDoctor.contacto.nombreCompleto").regex(name));
		}
		if (status != null) {
			query.addCriteria(Criteria.where("datosAuditoria.active").is(status));
		}
		if (cedula != null) {
			query.addCriteria(Criteria.where("datosDoctor.cedula").regex(cedula));
		}
		if (datosDoctorLlenos) {
			query.addCriteria(Criteria.where("datosDoctor").ne(null));
		}
		if(role != null ) {
			String rs [] = role.split(",");
			query.addCriteria(Criteria.where("roles").in(new HashSet<String>(Arrays.asList(rs)) ));
		}
		if (sort != null) {
			query.with(new Sort(sort, "datosAuditoria.creacion"));
		}
		if (page != null && results != null) {
			Pageable pageableRequest = new PageRequest(page, results);
			query.with(pageableRequest);
		}
		query.fields().include("nombre").include("roles").include("datosDoctor").include("datosAuditoria.active");

		List<Usuario> users = getTemplate().find(query, Usuario.class );

		return users;
	}

	public Long buscarTotalUsuarios(String name, String email, Boolean status , String cedula , String role, boolean datosDoctorLlenos) {
		Query query = new Query();
		if (name != null) {
			query.addCriteria(Criteria.where("nombre").regex("email"));
		}
		if (email != null) {
			query.addCriteria(Criteria.where("datosDoctor.contacto.nombreCompleto").regex("name"));
		}
		if (status != null) {
			query.addCriteria(Criteria.where("datosAuditoria.active").is(status));
		}
		if (cedula != null) {
			query.addCriteria(Criteria.where("datosDoctor.cedula").regex(cedula));
		}
		if (datosDoctorLlenos) {
			query.addCriteria(Criteria.where("datosDoctor").ne(null));
		}
		if(role != null ) {
			query.addCriteria(Criteria.where("roles").in(role));
		}

		return getTemplate().count(query, Usuario.class);
	}

}
