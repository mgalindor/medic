package com.mk.mnx.mdc.admUser.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.mdc.admUser.service.AdmUsuarioService;
import com.mk.mnx.mdc.model.domain.Doctor;
import com.mk.mnx.mdc.model.domain.NotaMedica;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.MDCRole;
import com.mk.mnx.mdc.support.exception.SimpleMDCException;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RestController
@RequestMapping("usuario")
public class AdmUsuarioRestController extends BaseRestController {
	
	@Autowired
	private AdmUsuarioService admUsuarioService;
	
	@PostMapping
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Usuario  registroUsuario(@RequestBody Usuario u) throws SimpleMDCException {
		logger.debug("Info recivida [{}]",u);
		return admUsuarioService.registroUsuario(u);
	}
	
	@GetMapping(params="id")
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Usuario  buscaUsuarioPorId(@RequestParam("id") String id) {
		return admUsuarioService.buscaUsuarioPorId(id);
	}
	
	@GetMapping(params="nombre")
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Usuario  buscaUsuarioPorNombre(@RequestParam String nombre) {
		return admUsuarioService.buscaUsuarioPorNombre(nombre);
	}
	
	@PutMapping
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public Usuario  actualizaUsuario(@RequestBody Usuario u) throws SimpleMDCException {
		return admUsuarioService.actualizaUsuario(u);
	}
	
	@DeleteMapping
	//@AccessValidation(roles= {MDCRole.ADMIN})
	public void  borraUsuario(@RequestBody Usuario u) {
		admUsuarioService.borraUsuario(u);
	}
	
	@GetMapping("/usuario")
	public Usuario  getUsuario() {
		PodamFactory factory = new PodamFactoryImpl();
		Usuario u = factory.manufacturePojo(Usuario.class);
		u.setCreacion(new Date());
		u.setRoles(Arrays.asList(MDCRole.values()));
		return u;
	}
	
	@GetMapping("/doctor")
	public Doctor  getDoctor() {
		PodamFactory factory = new PodamFactoryImpl();
		Doctor u = factory.manufacturePojo(Doctor.class);
		return u;
	}
	
	@GetMapping("/paciente")
	public Paciente  getPaciente() {
		PodamFactory factory = new PodamFactoryImpl();
		Paciente u = factory.manufacturePojo(Paciente.class);
		return u;
	}
	
	@GetMapping("/nota")
	public NotaMedica  getNota() {
		PodamFactory factory = new PodamFactoryImpl();
		NotaMedica u = factory.manufacturePojo(NotaMedica.class);
		return u;
	}

}
