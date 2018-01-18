package com.mk.mnx.mdc.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.model.domain.DatosDoctor;
import com.mk.mnx.mdc.model.domain.FootPrint;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.user.repository.UserCustomRepository;
import com.mk.mnx.mdc.user.repository.UsuarioRepository;

@Service
public class DoctorService extends BaseService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UserCustomRepository userCustomRepository;
	
	public  DatosDoctor crearDoctor(String idUser, DatosDoctor doctor , String currentUser) {
		Usuario u = usuarioRepository.findOne(idUser);
		validaUsuarioDoctor(u);
		if (u.getDatosDoctor() != null) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "No es posible crear el doctor debido a que ya existe uno creado");
		}

		validaDoctorRegistroDoctor(doctor);
		
		Usuario usuCedula = usuarioRepository.findDoctorCedula(doctor.getCedula());
		if (usuCedula != null && !usuCedula.getId().equals(idUser)) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "El numero de cedula ya esta registrado");
		}
		
		doctor.setCreacion(new Date());
		doctor.getContacto().setNombreCompleto(doctor.getContacto().creaNombreCompleto());
		u.setDatosDoctor(doctor);
		
		u.getDatosAuditoria().setActive(true);
		u.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),"crearDoctor"));
		
		usuarioRepository.save(u);
		return doctor;
	}
	
	private void validaUsuarioDoctor(Usuario usuario) {
		if (usuario == null ) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST,"El usuarios no existe");
		}
		else if (!usuario.getDatosAuditoria().isActive()) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST,"El usuario no esta activo");
		}
		else if (!usuario.getRoles().contains(EnuRole.DOCTOR)) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST,"El usuario no tiene el rol Doctor");
		}
	}
	
	private void validaDoctorRegistroDoctor(DatosDoctor doctor) {
		List<String> errors = new ArrayList<String>();
		if (doctor.getContacto().getNombre() == null || doctor.getContacto().getNombre().trim().equals("")) {
			errors.add("El nombre es un dato obligatorio");
		}
		if (doctor.getContacto().getApPaterno() == null || doctor.getContacto().getApPaterno().trim().equals("")) {
			errors.add("El apellido paterno es un dato obligatorio");
		}
		if (doctor.getContacto().getEmail() == null || doctor.getContacto().getEmail().trim().equals("") ) {
			errors.add("El email es un dato obligatorio");
		}
		if (doctor.getCedula() == null || doctor.getCedula().trim().equals("") ) {
			errors.add("El numero de cedula es un dato obligatorio");
		}

		if (!errors.isEmpty()) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "Error al registrar usuario", errors);
		}
	}
	
	public  DatosDoctor actualizarDoctor(String idUser, DatosDoctor doctor , String currentUser) {
		Usuario u = usuarioRepository.findOne(idUser);
		validaUsuarioDoctor(u);
		if (u.getDatosDoctor() == null) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "No es posible actualizar el doctor debido a que no existe uno creado");
		}
		validaDoctorRegistroDoctor(doctor);
		
		Usuario usuCedula = usuarioRepository.findDoctorCedula(doctor.getCedula());
		if (usuCedula != null && !usuCedula.getId().equals(idUser)) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "El numero de cedula ya esta registrado");
		}
		
		doctor.getContacto().setNombreCompleto(doctor.getContacto().creaNombreCompleto());
		u.setDatosDoctor(doctor);
		
		u.getDatosAuditoria().setActive(true);
		u.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(), "actualizarDoctor"  ));
		
		usuarioRepository.save(u);
		return doctor;
	}
	
	public  DatosDoctor buscarDoctor(String idUser) {
		Usuario u = usuarioRepository.findOne(idUser);
		validaUsuarioDoctor(u);
		if (u.getDatosDoctor() == null) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "No es posible actualizar el doctor debido a que no existe uno creado");
		}
		return u.getDatosDoctor();
	}
	
}
