package com.mk.mnx.mdc.user.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mk.mnx.mdc.model.domain.Usuario;

public interface UserCustomRepository {

	public List<Usuario> buscarUsuarios(String name, String email, Boolean status, Sort.Direction sort, Integer page,
			Integer results);

	public Long buscarTotalUsuarios(String name, String email, Boolean status);
}
