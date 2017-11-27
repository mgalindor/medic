package com.mk.mnx.mdc.admPaciente.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.mdc.admPaciente.service.AdmPacienteService;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.support.exception.SimpleMDCException;

@RestController
@RequestMapping("paciente")
public class AdmPacienteRestController extends BaseRestController {
	
	@Autowired
	private AdmPacienteService service;
	
	@PostMapping
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Paciente  registro(@RequestBody Paciente d) throws SimpleMDCException, IllegalAccessException, InvocationTargetException {
		logger.debug("Info recivida [{}]",d);
		return service.registro(d);
	}
	
	@GetMapping(params="id")
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Paciente  buscaPorId(@RequestParam("id") String id) {
		return service.buscaPorId(id);
	}
	
	@PutMapping
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Paciente  actualiza(@RequestBody Paciente d) throws SimpleMDCException {
		return service.actualiza(d);
	}	

}
