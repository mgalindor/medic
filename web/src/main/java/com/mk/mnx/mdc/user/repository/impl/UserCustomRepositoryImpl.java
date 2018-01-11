package com.mk.mnx.mdc.user.repository.impl;

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
			Integer results) {

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
		if (sort != null) {
			query.with(new Sort(sort, "id"));
		}
		if (page != null && results != null) {
			Pageable pageableRequest = new PageRequest(page, results);
			query.with(pageableRequest);
		}

		List<Usuario> users = getTemplate().find(query, Usuario.class);

		return users;
	}

	public Long buscarTotalUsuarios(String name, String email, Boolean status) {
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

		return getTemplate().count(query, Usuario.class);
	}

}
