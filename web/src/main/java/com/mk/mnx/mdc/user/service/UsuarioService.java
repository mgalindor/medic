package com.mk.mnx.mdc.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mk.mnx.infr.exception.HttpCodeException;
import com.mk.mnx.infr.service.BaseService;
import com.mk.mnx.mdc.model.domain.DatosAuditoria;
import com.mk.mnx.mdc.model.domain.FootPrint;
import com.mk.mnx.mdc.model.domain.Usuario;
import com.mk.mnx.mdc.model.states.EnuRole;
import com.mk.mnx.mdc.model.states.EnuTipoCambio;
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
		Usuario v = usuarioRepository.findByNombre(usuario.getNombre());
		if (v != null) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
		}
		Usuario u = new Usuario();
		u.setDatosAuditoria(new DatosAuditoria());
		u.getDatosAuditoria().setCreacion(new Date());
		u.getDatosAuditoria().setActive(true);
		u.getDatosAuditoria().setIdUsuarioCreacion(currentUser);

		u.setNombre(usuario.getNombre());
		u.setPassword(beanHelper.encode(usuario.getPassword()));
		u.setIsConnected(false);
		List<EnuRole> roles = new ArrayList<EnuRole>( Arrays.asList(EnuRole.USER));
		roles.addAll(usuario.getRoles());
		u.setRoles( new HashSet<EnuRole>(roles) );

		usuarioRepository.insert(u);
		u.setPassword(null);
		u.setDatosDoctor(null);
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
		if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
			errors.add("Los roles no pueden ser vacios");
		}

		if (!errors.isEmpty()) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "Error al registrar usuario", errors);
		}
	}
	
	private void validaActualizacionUsuario(Usuario usuario) {
		List<String> errors = new ArrayList<String>();
		if (usuario.getNombre() == null || usuario.getNombre().trim().equals("")) {
			errors.add("El nombre no puede ser vacio");
		}
		
		if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
			errors.add("Los roles no pueden ser vacios");
		}

		if (!errors.isEmpty()) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "Error al registrar usuario", errors);
		}
	}

	public Usuario actualizaUsuario(final Usuario nuevo, String currentUser) {
		validaActualizacionUsuario(nuevo);
		Usuario v = usuarioRepository.findByNombre(nuevo.getNombre());
		if (v != null && !v.getId().equals(nuevo.getId())) {
			throw new HttpCodeException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya esta registrado");
		}

		Usuario original = usuarioRepository.findOne(nuevo.getId());
		if (nuevo.getPassword() != null) {
			original.setPassword(beanHelper.encode(nuevo.getPassword()));
		}
		original.setNombre(nuevo.getNombre());
		List<EnuRole> roles = new ArrayList<EnuRole>( Arrays.asList(EnuRole.USER));
		roles.addAll(nuevo.getRoles());
		original.setRoles( new HashSet<EnuRole>(roles)  );
		original.getDatosAuditoria().setActive(true);
		original.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.ACTUALIZAR_USUARIO));
		usuarioRepository.save(original);
		
		original.setPassword(null);
		original.setDatosDoctor(null);
		return original;
	}

	public void borraUsuario(Usuario usuario, String currentUser) {
		Usuario original = usuarioRepository.findOne(usuario.getId());
		original.getDatosAuditoria().setActive(false);
		original.getDatosAuditoria().addModificado(new FootPrint(currentUser, new Date(),EnuTipoCambio.BORRAR_USUARIO));
		usuarioRepository.save(original);
	}

	public List<Usuario> buscarUsuarios(String name, String email, Boolean status, Sort.Direction sort, Integer page,
			Integer results  , String cedula ,  String  role) {
			return userCustomRepository.buscarUsuarios(name, email, status, sort, page, results , cedula, role);
	}

	public Long buscarTotalUsuarios(String name, String email, Boolean status,String cedula ,  String  role) {
			return userCustomRepository.buscarTotalUsuarios(name, email, status, cedula, role  );
	}

	public Usuario buscaUsuarioPorId(final String id) {
		Usuario u = usuarioRepository.findOne(id);
		u.setPassword(null);
		u.setDatosDoctor(null);
		u.setDatosAuditoria(null);
		return u;
	}

}
