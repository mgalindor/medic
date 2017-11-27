package com.mk.mnx.mdc.admUser.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.admUser.repository.UsuarioRepository;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.MDCRole;
import com.mk.mnx.mdc.support.exception.SimpleMDCException;
import com.mk.mnx.mdc.support.helper.BeanHelper;

@Service
public class AdmUsuarioService extends BaseService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BeanHelper beanHelper; 
	
	public Usuario registroUsuario(Usuario n) throws SimpleMDCException {
		logger.debug("a registrar [{}]", n);
		
		Usuario v = buscaUsuarioPorNombre(n.getNombre());
		if(v != null) {
			throw new SimpleMDCException("El nombre de usuario ya existe");
		}
		Usuario u = new Usuario ();
		u.setCreacion(new Date());
		u.setEnable(true);
		u.setNombre(n.getNombre());
		u.setPassword(DigestUtils.md5DigestAsHex(n.getPassword().getBytes()));
		u.setIsConnected(false);
		u.setRoles(Arrays.asList(MDCRole.USER));
		
		usuarioRepository.insert(u);
		
		logger.debug("registrado [{}]",u);
		return u;
	}

	public Usuario buscaUsuarioPorId(final String  id) {
		logger.debug("User id [{}]",id);
		Usuario r = usuarioRepository.findOne(id);
		logger.debug("Encontrado [{}]",r);
		return r;
	}
	
	public Usuario buscaUsuarioPorNombre(final String userName) {
		logger.debug("User name [{}]",userName);
		Usuario r = usuarioRepository.findByNombre(userName);
		logger.debug("Encontrado [{}]",r);
		return r;
	}

	public Usuario actualizaUsuario(final Usuario nuevo) throws SimpleMDCException {
		Usuario v = buscaUsuarioPorNombre(nuevo.getNombre());
		if(v != null  &&  !v.getId().equals(nuevo.getId())) {
			throw new SimpleMDCException("El nombre de usuario ya existe");
		}
		
		Usuario original =  buscaUsuarioPorId(nuevo.getId());
		nuevo.setCreacion(null);
		nuevo.setEnable(true);
		if(nuevo.getPassword() != null) {
			nuevo.setPassword(DigestUtils.md5DigestAsHex(nuevo.getPassword().getBytes()));
		}
		try {
			beanHelper.copyProperties(original, nuevo);
			usuarioRepository.save(original);
		} catch (Exception e) {
			logger.error("Ocurrio un error:",e);
			throw new SimpleMDCException("Ocurrio un error al realizar la actualización");
		} 
		return original;
	}

	public void borraUsuario(Usuario u) {
		Usuario original =  buscaUsuarioPorId (u.getId());
		original.setEnable(false);
		usuarioRepository.save(original);
	}
	

}
