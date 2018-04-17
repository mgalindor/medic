package com.mk.mnx.mdc.notas.repository.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mk.mnx.infr.repository.BaseCustomRepository;
import com.mk.mnx.mdc.model.domain.Nota;
import com.mk.mnx.mdc.notas.repository.NotaCustomRepository;

@Repository
public class NotaCustomRepositoryImpl extends BaseCustomRepository implements NotaCustomRepository{

	@Override
	public Nota getNotaOfUserByIndex(String idPaciente, Integer index) {
		Criteria criteria = Criteria.where("idPaciente").is(idPaciente).and("datosAuditoria.active").is(true);
		Query query = Query.query(criteria);
		query.limit(1);
		query.skip(index);
		query.with(new Sort(new Order(Direction.ASC, "datosAuditoria.creacion")));
		return getTemplate().findOne(query, Nota.class);
	}
	
	@Override
	public List<Nota> getNotaOfUserByIdUSuario(String idPaciente) {
		Criteria criteria = Criteria.where("idPaciente").is(idPaciente).and("datosAuditoria.active").is(true);
		Query query = Query.query(criteria);
		query.with(new Sort(new Order(Direction.ASC, "datosAuditoria.creacion")));
		return getTemplate().find(query, Nota.class);
	}

}
