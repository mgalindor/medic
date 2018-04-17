package com.mk.mnx.mdc.notas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.mdc.model.domain.Nota;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.notas.service.NotaService;
import com.mk.mnx.mdc.support.annotation.AccessValidation;

@RestController
@RequestMapping("nota")
public class NotaController extends BaseRestController {
	
	@Autowired
	private NotaService notaService;
	
	@PostMapping
	@AccessValidation(roles = {  EnuRole.MEDICO })
	public Nota creaNota( @RequestBody Nota nota ) {
		return notaService.creaNota(nota, getUser());
	}
	
	@PutMapping
	@AccessValidation(roles = {  EnuRole.MEDICO })
	public Nota actualizaNota( @RequestBody Nota nota  ) {
		return notaService.actualizaNota(nota, getUser());
	}
	
	@GetMapping("/{idNota}" )
	@AccessValidation(roles = {  EnuRole.MEDICO })
	public Nota buscaNota(@PathVariable("idNota") String idNota  ) {
		return notaService.buscaNota( idNota);
	}
	
	@DeleteMapping("/{idNota}" )
	@AccessValidation(roles = {  EnuRole.ADMIN })
	public void borrarNota( @PathVariable("idNota") String idNota ) {
		notaService.borrarNota(idNota, getUser());
	}
	
}
