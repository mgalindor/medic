package com.mk.mnx.mdc.model.domain;

import com.mk.mnx.infr.model.BaseModel;

public class TensionAbdominal extends BaseModel {
	
	public Integer presionSistolica;
	
	public Integer presidonDiastlica;

	public Integer getPresionSistolica() {
		return presionSistolica;
	}

	public void setPresionSistolica(Integer presionSistolica) {
		this.presionSistolica = presionSistolica;
	}

	public Integer getPresidonDiastlica() {
		return presidonDiastlica;
	}

	public void setPresidonDiastlica(Integer presidonDiastlica) {
		this.presidonDiastlica = presidonDiastlica;
	}
	
}
