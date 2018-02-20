package com.mk.mnx.mdc.paciente.repository.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mk.mnx.infr.repository.BaseCustomRepository;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.states.EnuSexo;
import com.mk.mnx.mdc.paciente.repository.PacienteCustomRepository;

@Repository
public class PacienteCustomRepositoryImpl extends BaseCustomRepository implements PacienteCustomRepository {

	public List<Paciente> buscarPasientes(String nombre,String curp, EnuSexo sexo , Boolean status, String createdBy,Direction sort, Integer page,
			Integer results) {
		Query query = new Query();
		if (nombre != null) {
			query.addCriteria(Criteria.where("contacto.nombreCompleto").regex(nombre));
		}
		if (curp != null) {
			query.addCriteria(Criteria.where("curp").regex(curp));
		}
		if (sexo != null) {
			query.addCriteria(Criteria.where("sexo").is(sexo));
		}
		if (status != null) {
			query.addCriteria(Criteria.where("datosAuditoria.active").is(status));
		}
		if (createdBy != null) {
			query.addCriteria(Criteria.where("datosAuditoria.idUsuarioCreacion").regex(createdBy));
		}
		if (sort != null) {
			query.with(new Sort(sort, "datosAuditoria.creacion"));
		}
		if (page != null && results != null) {
			Pageable pageableRequest = new PageRequest(page, results);
			query.with(pageableRequest);
		}
		query.fields().include("contacto").include("fecNacimiento").include("sexo").include("diagnostico").include("curp").include("datosAuditoria.active");
		List<Paciente> pacientes = getTemplate().find(query, Paciente.class );
		return pacientes;
	}
	
	public Long buscarTotalPasientes(String nombre, String curp, EnuSexo sexo , Boolean status, String createdBy) {
		Query query = new Query();
		if (nombre != null) {
			query.addCriteria(Criteria.where("contacto.nombreCompleto").regex(nombre));
		}
		if (curp != null) {
			query.addCriteria(Criteria.where("curp").regex(curp));
		}
		if (sexo != null) {
			query.addCriteria(Criteria.where("sexo").is(sexo));
		}
		if (status != null) {
			query.addCriteria(Criteria.where("datosAuditoria.active").is(status));
		}
		if (createdBy != null) {
			query.addCriteria(Criteria.where("datosAuditoria.idUsuarioCreacion").regex(createdBy));
		}
		
		return  getTemplate().count(query, Paciente.class);
	}
}
