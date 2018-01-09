package com.mk.mnx.mdc.user.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.mdc.model.domain.DatosDoctor;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.support.annotation.AccessValidation;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RestController
@RequestMapping("usuario")
public class UsuarioController extends BaseRestController{
	
	@PostMapping("/createTemporalAdmin")
	public Usuario createTemporalAdmin( @RequestBody Usuario u ) {
		return u;
	}
	
	@PostMapping
	@AccessValidation(roles= {EnuRole.ADMIN})
	public Usuario creaUsuario( @RequestBody Usuario u ) {
		return u;
	}
	
	@PutMapping
	@AccessValidation(roles= {EnuRole.ADMIN})
	public Usuario actualizaUsuario( @RequestBody Usuario u ) {
		return u;
	}
	
	@GetMapping
	@AccessValidation(roles= {EnuRole.ADMIN})
	public List<Usuario> buscaUsuariosPorNombre(@RequestParam(value="name",required=false) String name) {
		PodamFactory factory = new PodamFactoryImpl();
		Usuario u = factory.manufacturePojo(Usuario.class);
		u.setRoles(Arrays.asList(EnuRole.values()));
		
		Usuario u2 = factory.manufacturePojo(Usuario.class);
		u2.setRoles(Arrays.asList(EnuRole.values()));
		return Arrays.asList(u,u2);
	}
	
	@GetMapping("/{idUser}" )
	@AccessValidation(roles= {EnuRole.ADMIN})
	public Usuario buscaUsuario(@PathVariable("idUser") String idUser ) {
		PodamFactory factory = new PodamFactoryImpl();
		Usuario u = factory.manufacturePojo(Usuario.class);
		u.setRoles(Arrays.asList(EnuRole.values()));
		return u;
	}
	
	@DeleteMapping
	@AccessValidation(roles= {EnuRole.ADMIN})
	public Usuario borraUsuario( @RequestBody Usuario u ) {
		return u;
	}
	
	@PostMapping("/{idUser}/doctor/")
	@AccessValidation(roles= {EnuRole.ADMIN})
	public DatosDoctor creaDoctor( @RequestBody DatosDoctor doc ) {
		return doc;
	}
	
	@PutMapping("/{idUser}/doctor/")
	@AccessValidation(roles= {EnuRole.ADMIN,EnuRole.MEDICO})
	public DatosDoctor actualizaDatosDoctor(@RequestBody DatosDoctor doc) {
		return doc;
	}

	@GetMapping("/{idUser}/doctor/")
	@AccessValidation(roles= {EnuRole.ADMIN,EnuRole.MEDICO})
	public DatosDoctor buscaDoctor(  ) {
		PodamFactory factory = new PodamFactoryImpl();
		DatosDoctor datos =  factory.manufacturePojo(DatosDoctor.class);
		return datos;
	}
	
}
