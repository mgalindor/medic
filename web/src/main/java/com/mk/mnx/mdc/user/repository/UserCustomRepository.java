package com.mk.mnx.mdc.user.repository;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.mk.mnx.mdc.model.domain.Usuario;

public interface UserCustomRepository {

	public List<Usuario> buscarUsuarios(String name, String email, Boolean status, Direction sort, Integer page,
			Integer results , String cedula , String role ,boolean datosDoctorLlenos);

	public Long buscarTotalUsuarios(String name, String email, Boolean status , String cedula , String role,boolean datosDoctorLlenos);
}
