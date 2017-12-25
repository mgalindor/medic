package com.mk.mnx.mdc.notas.repository.impl;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mk.mnx.infr.repository.BaseCustomRepository;
import com.mk.mnx.mdc.model.domain.Nota;
import com.mk.mnx.mdc.notas.repository.NotaCustomRepository;

public class NotaCustomRepositoryImpl extends BaseCustomRepository implements NotaCustomRepository{

	public Nota findByIndex(String idUsuario, Integer index) {
		Criteria criteria = Criteria.where("_id").is(idUsuario);
		Query query = Query.query(criteria);
		query.limit(1);
		query.skip(index);
		query.with(new Sort(new Order(Direction.ASC, "datosAuditoria.creacion")));
		return getTemplate().findOne(query, Nota.class);
	}

}
