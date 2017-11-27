package com.mk.mnx.mdc.admDoctor.controller;

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
import com.mk.mnx.mdc.admDoctor.service.AdmDoctorService;
import com.mk.mnx.mdc.model.domain.Doctor;
import com.mk.mnx.mdc.support.exception.SimpleMDCException;

@RestController
@RequestMapping("doctor")
public class AdmDoctorRestController extends BaseRestController {
	
	@Autowired
	private AdmDoctorService service;
	
	@PostMapping
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Doctor  registro(@RequestBody Doctor d) throws SimpleMDCException, IllegalAccessException, InvocationTargetException {
		logger.debug("Info recivida [{}]",d);
		return service.registro(d);
	}
	
	@GetMapping(params="id")
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Doctor  buscaPorId(@RequestParam("id") String id) {
		return service.buscaPorId(id);
	}
	
	@GetMapping(params="nombre")
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Doctor  buscaPorIdUsuario(@RequestParam String idUsuario) {
		return service.buscaPorIdUsuario(idUsuario);
	}
	
	@PutMapping
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Doctor  actualiza(@RequestBody Doctor d) throws SimpleMDCException {
		return service.actualiza(d);
	}	

}
