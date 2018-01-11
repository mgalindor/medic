package com.mk.mnx.mdc.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.model.domain.DatosAuditoria;
import com.mk.mnx.mdc.model.domain.FootPrint;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.support.helper.BeanHelper;
import com.mk.mnx.mdc.user.repository.UserCustomRepository;
import com.mk.mnx.mdc.user.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService {

	@Autowired
	private BeanHelper beanHelper;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	UserCustomRepository userCustomRepository;

	public Usuario creaUsuario(Usuario usuario, String currentUser) {
		logger.debug("a registrar [{}]", usuario);
		validaRegistroUsuario(usuario);
		Usuario v = buscaUsuarioPorNombre(usuario.getNombre());
		if (v != null) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "El nombre de usuario ya existe");
		}
		Usuario u = new Usuario();
		u.setDatosAuditoria(new DatosAuditoria());
		u.getDatosAuditoria().setCreacion(new Date());
		u.getDatosAuditoria().setActive(true);
		u.getDatosAuditoria().setIdUsuarioCreacion(currentUser);

		u.setNombre(usuario.getNombre());
		u.setPassword(beanHelper.encode(usuario.getPassword()));
		u.setIsConnected(false);
		u.setRoles(usuario.getRoles());

		usuarioRepository.insert(u);

		logger.debug("registrado [{}]", u);
		return u;
	}

	private void validaRegistroUsuario(Usuario usuario) {
		List<String> errors = new ArrayList<String>();
		if (usuario.getNombre() == null || usuario.getNombre().trim().equals("")) {
			errors.add("El nombre no puede ser vacio");
		}
		if (usuario.getPassword() == null || usuario.getPassword().trim().equals("")) {
			errors.add("El password no puede ser vacio");
		}

		if (!errors.isEmpty()) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "Error al registrar usuario", errors);
		}
	}

	public Usuario actualizaUsuario(final Usuario nuevo, String currentUser) {
		validaRegistroUsuario(nuevo);
		Usuario v = buscaUsuarioPorNombre(nuevo.getNombre());
		if (v != null && !v.getId().equals(nuevo.getId())) {
			throw new HttpCodeException(HttpCodeException.CODES.SC_BAD_REQUEST, "El nombre de usuario ya existe");
		}

		Usuario original = buscaUsuarioPorId(nuevo.getId());
		if (nuevo.getPassword() != null) {
			original.setPassword(beanHelper.encode(nuevo.getPassword()));
		}
		original.setNombre(nuevo.getNombre());
		original.setRoles(nuevo.getRoles());
		original.getDatosAuditoria().setActive(true);
		original.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date()));
		usuarioRepository.save(original);
		return original;
	}

	public void borraUsuario(Usuario usuario, String currentUser) {
		Usuario original = buscaUsuarioPorId(usuario.getId());
		original.getDatosAuditoria().setActive(false);
		original.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date()));
		usuarioRepository.save(original);
	}

	public List<Usuario> buscarUsuarios(String name, String email, Boolean status, Sort.Direction sort, Integer page,
			Integer results) {
		return userCustomRepository.buscarUsuarios(name, email, status, sort, page, results);
	}

	public Long buscarTotalUsuarios(String name, String email, Boolean status) {
		return userCustomRepository.buscarTotalUsuarios(name, email, status);
	}

	public Usuario buscaUsuarioPorId(final String id) {
		logger.debug("User id [{}]", id);
		Usuario r = usuarioRepository.findOne(id);
		return r;
	}

	private Usuario buscaUsuarioPorNombre(final String userName) {
		logger.debug("User name [{}]", userName);
		Usuario r = usuarioRepository.findByNombre(userName);
		return r;
	}

}
