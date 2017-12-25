package com.mk.mnx.mdc.notas.repository;

import com.mk.mnx.mdc.model.domain.Nota;

public interface NotaCustomRepository {
	
	public Nota findByIndex(String idUsuario ,Integer index);

}
