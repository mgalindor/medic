package com.mk.mnx.mdc.paciente.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.mdc.model.domain.DatosAuditoria;
import com.mk.mnx.mdc.model.domain.FootPrint;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.states.EnuSexo;
import com.mk.mnx.mdc.model.states.EnuTipoCambio;
import com.mk.mnx.mdc.paciente.repository.PacienteCustomRepository;
import com.mk.mnx.mdc.paciente.repository.PacienteRepository;
import com.mk.mnx.mdc.paciente.service.PacienteService;
import com.mk.mnx.vld.annotation.Validate;

@Service
public class PacienteServiceImpl  implements PacienteService{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteCustomRepository pacienteCustomRepository;
	
	@Validate
	@Override
	public Paciente creaPaciente( Paciente paciente , String currentUser) {
		Paciente original = pacienteRepository.findByCurp(paciente.getCurp());
		if (original != null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "Ya existe un paciente con el mismo curp");
		}
		paciente.getContacto().setNombreCompleto(paciente.getContacto().creaNombreCompleto());
		paciente.setDatosAuditoria(new DatosAuditoria());
		paciente.getDatosAuditoria().setCreacion(new Date());
		paciente.getDatosAuditoria().setActive(true);
		paciente.getDatosAuditoria().setIdUsuarioCreacion(currentUser);
		
		paciente.setHistoriaClinica(null);
		pacienteRepository.insert(paciente);
		return paciente;
	}
	
	@Validate
	@Override
	public Paciente actualizaPaciente( Paciente paciente, String currentUser ) {
		
		Paciente original = pacienteRepository.findByCurp(paciente.getCurp());
		if (original != null  && paciente.getId().equals(original.getId())) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "Ya existe un paciente con el mismo curp");
		}
		paciente.getContacto().setNombreCompleto(paciente.getContacto().creaNombreCompleto());
		paciente.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.ACTUALIZAR_PACIENTE));
		pacienteRepository.save(original);
		return paciente;
	}
	
	@Validate
	@Override
	public Paciente borraPaciente( Paciente paciente, String currentUser ) {
		Paciente original = pacienteRepository.findOne(paciente.getId());
		original.getDatosAuditoria().setActive(false);
		original.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.BORRAR_PACIENTE));
		pacienteRepository.save(original);
		return paciente;
	}
	
	@Validate
	@Override
	public Paciente buscaPaciente(String idPaciente ) {
		Paciente paciente = pacienteRepository.findOne(idPaciente);
		paciente.setHistoriaClinica(null);
		paciente.getDatosAuditoria().setIdUsuarioCreacion(null);
		paciente.getDatosAuditoria().setModificado(null);
		return paciente;
	}
	
	@Override
	public List<Paciente> buscaPacientes(String name,  String curp, EnuSexo sexo, String createdBy,
			 Boolean active,Sort.Direction sort,Integer page, Integer results) {
		return pacienteCustomRepository.buscarPasientes(name, curp, sexo, active, createdBy, sort, page, results);
	}
	
	@Override
	public Long buscaTotalPacientes(String name,  String curp, EnuSexo sexo,String createdBy,
			 Boolean active) {
		return pacienteCustomRepository.buscarTotalPasientes(name, curp, sexo, active, createdBy);
	}
}
