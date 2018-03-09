package com.mk.mnx.mdc.paciente.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.mdc.model.domain.AntecedentesGinecoObstetra;
import com.mk.mnx.mdc.model.domain.DatosAuditoria;
import com.mk.mnx.mdc.model.domain.FootPrint;
import com.mk.mnx.mdc.model.domain.HistoriaClinica;
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

		Paciente ex = pacienteRepository.findOne(paciente.getId());
		if(ex == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no existe");
		}
		
		if (original != null  && !paciente.getId().equals(original.getId())) {
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
		if(original == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no existe");
		}
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
	
	@Override
	@Validate
	public HistoriaClinica creaHistoria(HistoriaClinica historia,  String idPaciente ,  String currentUser) {
		Paciente paciente = pacienteRepository.findOne(idPaciente);
		if(paciente == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no existe");
		}
		
		if(paciente != null && paciente.getHistoriaClinica() != null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente ya cuenta con Historial Clinico");
		}
		
		validaAntecedentesGinecoObstetra(historia.getAntecedentesGinecoObstetra(), paciente);
		paciente.setHistoriaClinica(historia);
		paciente.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.CREAR_HISTORIA));
		pacienteRepository.save(paciente);
		return historia;
	}
	
	@Override
	@Validate
	public HistoriaClinica actualizaHistoria(HistoriaClinica historia,  String idPaciente ,  String currentUser) {
		Paciente paciente = pacienteRepository.findOne(idPaciente);
		if(paciente == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no existe");
		}
		if(paciente != null && paciente.getHistoriaClinica() == null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no cuenta con Historial Clinico");
		}
		validaAntecedentesGinecoObstetra(historia.getAntecedentesGinecoObstetra(), paciente);
		paciente.setHistoriaClinica(historia);
		paciente.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.ACTUALIZAR_HISTORIA));
		pacienteRepository.save(paciente);
		return historia;
	}
	
	@Override
	public HistoriaClinica buscaHistoria(String idPaciente) {
		Paciente paciente = pacienteRepository.findOne(idPaciente);
		return paciente.getHistoriaClinica();
	}
	
	private void validaAntecedentesGinecoObstetra(AntecedentesGinecoObstetra antecedentesGinecoObstetra , Paciente paciente ) {
		if(paciente.getSexo().equals(EnuSexo.MASCULINO )  &&  antecedentesGinecoObstetra != null ) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente no requiere antecedentes gineco obstetra");
		}
		if(paciente.getSexo().equals(EnuSexo.FEMENINO )  &&  antecedentesGinecoObstetra == null ) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente requiere antecedentes gineco obstetra");
		}
		
		if(paciente.getSexo().equals(EnuSexo.FEMENINO )  &&  antecedentesGinecoObstetra != null ) {
			List<String> errors = new ArrayList<String>();
			if( antecedentesGinecoObstetra.getMenarca() == null) {
				errors.add("El campo es Menarca requerido");
			}
			if( antecedentesGinecoObstetra.getTelarca() == null) {
				errors.add("El campo es Telarca requerido");
			}
			if( antecedentesGinecoObstetra.getPubarca() == null) {
				errors.add("El campo es Pubarca requerido");
			}
			if( StringUtils.isBlank( antecedentesGinecoObstetra.getRitmo())) {
				errors.add("El campo es Ritmo requerido");
			}
			if( antecedentesGinecoObstetra.getIvsa() == null) {
				errors.add("El campo es IVSA requerido");
			}
			if( antecedentesGinecoObstetra.getNumParejas() == null) {
				errors.add("El campo es Número de parejas requerido");
			}
			if( antecedentesGinecoObstetra.getFum() == null) {
				errors.add("El campo es FUM requerido");
			}
			if( antecedentesGinecoObstetra.getG() == null) {
				errors.add("El campo es G requerido");
			}
			if( antecedentesGinecoObstetra.getP() == null) {
				errors.add("El campo es P requerido");
			}
			if( antecedentesGinecoObstetra.getC() == null) {
				errors.add("El campo es C requerido");
			}
			if( antecedentesGinecoObstetra.getA() == null) {
				errors.add("El campo es A requerido");
			}
			if( StringUtils.isBlank( antecedentesGinecoObstetra.getMetodoAnticonceptivo())) {
				errors.add("El campo es Método Anticonceptivo requerido");
			}
			if( antecedentesGinecoObstetra.getDismenorrea() == null) {
				errors.add("El campo es Dismenorrea requerido");
			}
			if( antecedentesGinecoObstetra.getGalacotorrea() == null) {
				errors.add("El campo es Galacotorrea requerido");
			}
			
			if(!errors.isEmpty()) {
				throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El paciente requiere los siguientes campos",errors);
			}
		}
		
	}
	
}
