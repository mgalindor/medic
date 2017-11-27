package com.mk.mnx.mdc.admPaciente.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.admPaciente.repository.PacienteRepository;
import com.mk.mnx.mdc.model.domain.Paciente;
import com.mk.mnx.mdc.support.exception.SimpleMDCException;
import com.mk.mnx.mdc.support.helper.BeanHelper;

@Service
public class AdmPacienteService extends BaseService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private BeanHelper beanHelper; 
	
	public Paciente registro(Paciente ori) throws SimpleMDCException, IllegalAccessException, InvocationTargetException {
		logger.debug("a registrar [{}]", ori);
		
		//Doctor v = doctorRepository.findByIdUsuario(ori.getIdUsuario());
		//if(v != null) {
		//	throw new SimpleMDCException("El doctor ya existe");
		//}
		
		Paciente dest = new Paciente ();
		
		beanHelper.copyProperties(dest, ori);
		dest.setId("");
		dest.setCreacion(new Date());
		pacienteRepository.insert(dest);
		
		return dest;
	}

	public Paciente buscaPorId(final String  id) {
		logger.debug("User id [{}]",id);
		Paciente r = pacienteRepository.findOne(id);
		logger.debug("Encontrado [{}]",r);
		return r;
	}
	

	public Paciente actualiza(final Paciente nuevo) throws SimpleMDCException {
		Paciente original =  buscaPorId(nuevo.getId());
		if(original == null  ) {
			throw new SimpleMDCException("No es posible actualizar al doctor");
		}
		
		
		nuevo.setCreacion(null);

		try {
			beanHelper.copyProperties(original, nuevo);
			pacienteRepository.save(original);
		} catch (Exception e) {
			logger.error("Ocurrio un error:",e);
			throw new SimpleMDCException("Ocurrio un error al realizar la actualización");
		} 
		return original;
	}

}
