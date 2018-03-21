package com.mk.mnx.mdc.notas.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.model.domain.DatosAuditoria;
import com.mk.mnx.mdc.model.domain.FootPrint;
import com.mk.mnx.mdc.model.domain.Nota;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.model.states.EnuTipoCambio;
import com.mk.mnx.mdc.notas.repository.NotaCustomRepository;
import com.mk.mnx.mdc.notas.repository.NotaRepository;
import com.mk.mnx.mdc.notas.service.NotaService;
import com.mk.mnx.mdc.paciente.repository.PacienteRepository;

@Service
public class NotaServiceImpl extends BaseService implements NotaService {

	@Autowired
	private NotaRepository notaRepository;
	
	@Autowired
	private NotaCustomRepository notaCustomRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Override
	public Nota creaNota( Nota nota, String currentUser) {
		
		validaPaciente(nota.getIdPaciente());
		
		nota.setDatosAuditoria(new DatosAuditoria());
		nota.getDatosAuditoria().setCreacion(new Date());
		nota.getDatosAuditoria().setActive(true);
		nota.getDatosAuditoria().setIdUsuarioCreacion(currentUser);
		nota.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.CREAR_NOTA));
		notaRepository.insert(nota);
		return nota;
	}
	
	@Override
	public Nota actualizaNota(Nota nota, String currentUser ) {
		Nota aux = notaRepository.findOne(nota.getId());
		if(aux == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "La nota no existe");
		}
		
		if(!aux.getIdPaciente().equals( nota.getIdPaciente() ) ) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "La nota no pertenece al usuario");
		}
		
		nota.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.ACTUALIZAR_NOTA));
		
		return nota;
	}
	
	@Override
	public List<Nota> buscaNotasPorPaciente(String idPaciente ) {
		return notaCustomRepository.getNotaOfUserByIdUSuario(idPaciente);
	}
	
	@Override
	public Nota buscaNotaPorIndice(String idPaciente , Integer index) {
		Nota aux = notaCustomRepository.getNotaOfUserByIndex(idPaciente, index);
		return aux;
	}
	
	@Override
	public Nota buscaNota(String idNota) {
		Nota aux = notaRepository.findOne(idNota);
		return aux;
	}
	
	@Override
	public void borrarNota(String idNota , String currentUser ) {
		Nota original = notaRepository.findOne(idNota);
		if(original == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "La nota no existe");
		}
		original.getDatosAuditoria().setActive(false);
		original.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.BORRAR_NOTA));
		notaRepository.save(original);
		return ;
	}
	
	private void validaPaciente(String idPaciente) {
		Paciente paciente = pacienteRepository.findOne(idPaciente);
		if(paciente == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no existe");
		}
		
		if( !paciente.getDatosAuditoria().isActive() ) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no se encuentra activo");
		}
	}
}
