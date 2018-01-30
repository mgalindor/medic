package com.mk.mnx.mdc.paciente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import com.mk.mnx.mdc.model.states.EnuSexo;
import com.mk.mnx.mdc.paciente.service.PacienteService;

@RestController
@RequestMapping("paciente")
public class PacienteController extends BaseRestController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping
	public Paciente creaPaciente( @RequestBody Paciente paciente ) {
		return pacienteService.creaPaciente(paciente, getUser());
	}
	
	@PutMapping
	public Paciente actualizaPaciente( @RequestBody Paciente paciente ) {
		return pacienteService.actualizaPaciente(paciente, getUser());
	}
	
	@GetMapping
	public List<Paciente> buscaPacientes(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "curp", required = false) String curp,
			@RequestParam(value = "sexo", required = false) EnuSexo sexo,
			@RequestParam(value = "createdBy", required = false) String createdBy,
			@RequestParam(value = "active", required = false) Boolean active,
			@RequestParam(value = "sort", required = false) Sort.Direction sort,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "results", required = false) Integer results) {
		
		return pacienteService.buscaPacientes(name, curp, sexo, createdBy, active, sort, page, results);
	}
	
	@GetMapping("/total")
	public Long buscaTotalPacientes(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "curp", required = false) String curp,
			@RequestParam(value = "sexo", required = false) EnuSexo sexo,
			@RequestParam(value = "createdBy", required = false) String createdBy,
			@RequestParam(value = "active", required = false) Boolean active) {
		
		return pacienteService.buscaTotalPacientes(name, curp, sexo, createdBy, active);
	}
	
	@GetMapping("/{idPaciente}" )
	public Paciente buscaPaciente(@PathVariable("idPaciente") String idPaciente ) {
		return pacienteService.buscaPaciente(idPaciente);
	}
	
	@DeleteMapping
	public void borraPaciente( @RequestBody Paciente paciente ) {
		 pacienteService.borraPaciente(paciente, getUser());
	}

}
