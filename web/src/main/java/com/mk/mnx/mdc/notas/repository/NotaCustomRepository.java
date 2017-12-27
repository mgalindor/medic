package com.mk.mnx.mdc.notas.repository;

import com.mk.mnx.mdc.model.domain.Nota;

public interface NotaCustomRepository {
	
	public Nota getNotaOfUserByIndex(String idUsuario ,Integer index);

}
