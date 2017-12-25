package com.mk.mnx.mdc.paciente.controller;

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
import com.mk.mnx.mdc.model.domain.Paciente;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RestController
@RequestMapping("paciente")
public class PacienteController extends BaseRestController {
	
	@PostMapping
	public Paciente creaPaciente( @RequestBody Paciente p ) {
		return p;
	}
	
	@PutMapping
	public Paciente actualizaPaciente( @RequestBody Paciente p ) {
		return p;
	}
	
	@GetMapping
	public List<Paciente> buscaPacientesPorNombre(@RequestParam(value="name",required=false) String name) {
		PodamFactory factory = new PodamFactoryImpl();
		Paciente u = factory.manufacturePojo(Paciente.class);
		
		Paciente u2 = factory.manufacturePojo(Paciente.class);
		return Arrays.asList(u,u2);
	}
	
	@GetMapping("/{idPaciente}" )
	public Paciente buscaPaciente(@PathVariable("idPaciente") String idPaciente ) {
		PodamFactory factory = new PodamFactoryImpl();
		Paciente u = factory.manufacturePojo(Paciente.class);
		return u;
	}
	
	@DeleteMapping
	public Paciente borraPaciente( @RequestBody Paciente p ) {
		return p;
	}

}
