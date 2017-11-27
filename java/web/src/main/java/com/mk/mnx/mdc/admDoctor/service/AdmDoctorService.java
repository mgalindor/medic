package com.mk.mnx.mdc.admDoctor.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.admDoctor.repository.DoctorRepository;
import com.mk.mnx.mdc.admDoctor.repository.UsuarioRepository;
import com.mk.mnx.mdc.model.domain.Doctor;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.support.exception.SimpleMDCException;
import com.mk.mnx.mdc.support.helper.BeanHelper;

@Service
public class AdmDoctorService extends BaseService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private BeanHelper beanHelper; 
	
	public Doctor registro(Doctor ori) throws SimpleMDCException, IllegalAccessException, InvocationTargetException {
		logger.debug("a registrar [{}]", ori);
		
		Doctor v = doctorRepository.findByIdUsuario(ori.getIdUsuario());
		if(v != null) {
			throw new SimpleMDCException("El doctor ya existe");
		}
		
		Doctor dest = new Doctor ();
		dest.setCreacion(new Date());
		
		
		beanHelper.copyProperties(dest, ori);
		dest.setId("");
		dest.setCreacion(new Date());
		doctorRepository.insert(dest);
		
		return dest;
	}

	public Doctor buscaPorId(final String  id) {
		logger.debug("User id [{}]",id);
		Doctor r = doctorRepository.findOne(id);
		logger.debug("Encontrado [{}]",r);
		return r;
	}
	
	public Doctor buscaPorIdUsuario(final String idUsuario) {
		logger.debug("User name [{}]",idUsuario);
		Doctor r = doctorRepository.findByIdUsuario(idUsuario);
		logger.debug("Encontrado [{}]",r);
		return r;
	}

	public Doctor actualiza(final Doctor nuevo) throws SimpleMDCException {
		Doctor original =  buscaPorId(nuevo.getId());
		if(original == null  ) {
			throw new SimpleMDCException("No es posible actualizar al doctor");
		}
		
		Usuario u = usuarioRepository.findOne(nuevo.getIdUsuario());
		if(u != null && !u.isEnable()) {
			throw new SimpleMDCException("No es posible actualizar al doctor");
		}
		
		
		nuevo.setCreacion(null);

		try {
			beanHelper.copyProperties(original, nuevo);
			doctorRepository.save(original);
		} catch (Exception e) {
			logger.error("Ocurrio un error:",e);
			throw new SimpleMDCException("Ocurrio un error al realizar la actualización");
		} 
		return original;
	}

}
