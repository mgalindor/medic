package com.mk.mnx.mdc.notas.service;

import java.util.List;

import com.mk.mnx.mdc.model.domain.Nota;

public interface NotaService {

	Nota creaNota(Nota nota, String currentUser);

	Nota actualizaNota(Nota nota, String currentUser);

	List<Nota> buscaNotasPorPaciente(String idPaciente);

	Nota buscaNota( String idNota);

	void borrarNota(String idNota, String currentUser);

}
