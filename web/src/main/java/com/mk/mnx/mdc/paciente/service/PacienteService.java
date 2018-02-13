package com.mk.mnx.mdc.paciente.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.model.domain.FootPrint;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.states.EnuSexo;
import com.mk.mnx.mdc.model.states.EnuTipoCambio;
import com.mk.mnx.mdc.paciente.repository.PacienteCustomRepository;
import com.mk.mnx.mdc.paciente.repository.PacienteRepository;

@Service
public class PacienteService extends BaseService{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteCustomRepository pacienteCustomRepository;
	
	public Paciente creaPaciente( Paciente paciente , String currentUser) {
		Paciente original = pacienteRepository.findByCurp(paciente.getCurp());
		
		return paciente;
	}
	
	public Paciente actualizaPaciente( Paciente paciente, String currentUser ) {
		return paciente;
	}
	
	public Paciente borraPaciente( Paciente paciente, String currentUser ) {
		Paciente original = pacienteRepository.findOne(paciente.getId());
		original.getDatosAuditoria().setActive(false);
		original.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.BORRAR_PACIENTE));
		pacienteRepository.save(original);
		return paciente;
	}
	
	public Paciente buscaPaciente(String idPaciente ) {
		Paciente paciente = pacienteRepository.findOne(idPaciente);
		paciente.setHistoriaClinica(null);
		paciente.getDatosAuditoria().setIdUsuarioCreacion(null);
		paciente.getDatosAuditoria().setModificado(null);
		return paciente;
	}
	
	public List<Paciente> buscaPacientes(String name,  String curp, EnuSexo sexo, String createdBy,
			 Boolean active,Sort.Direction sort,Integer page, Integer results) {
		return pacienteCustomRepository.buscarPasientes(name, curp, sexo, active, createdBy, sort, page, results);
	}
	
	public Long buscaTotalPacientes(String name,  String curp, EnuSexo sexo,String createdBy,
			 Boolean active) {
		return pacienteCustomRepository.buscarTotalPasientes(name, curp, sexo, active, createdBy);
	}
}
