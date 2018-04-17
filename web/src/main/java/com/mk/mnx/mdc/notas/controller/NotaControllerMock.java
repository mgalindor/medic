package com.mk.mnx.mdc.notas.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mk.mnx.infr.controller.BaseRestController;
import com.mk.mnx.mdc.model.domain.Nota;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RestController
@RequestMapping("nota/mock")
public class NotaControllerMock extends BaseRestController {
	
	@PostMapping
	public Nota creaNota( @RequestBody Nota n ) {
		return n;
	}
	
	@PutMapping("/{idPaciente}/{numNota}")
	public Nota actualizaNota( @RequestBody Nota n ) {
		return n;
	}
	
	@GetMapping("/{idPaciente}")
	public List<Nota> buscaNotasPorPaciente(@RequestParam(value="name",required=false) String name) {
		PodamFactory factory = new PodamFactoryImpl();
		Nota u = factory.manufacturePojo(Nota.class);
		
		Nota u2 = factory.manufacturePojo(Nota.class);
		return Arrays.asList(u,u2);
	}
	
	@GetMapping("/{idPaciente}/{numNota}" )
	public Nota buscaNota(@PathVariable("idNota") String idNota ) {
		PodamFactory factory = new PodamFactoryImpl();
		Nota u = factory.manufacturePojo(Nota.class);
		return u;
	}
	
}
