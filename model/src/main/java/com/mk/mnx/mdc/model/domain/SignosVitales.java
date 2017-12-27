package com.mk.mnx.mdc.model.domain;

import com.mk.mnx.infr.model.BaseModel;

public class SignosVitales extends BaseModel {
	
	private TensionAbdominal ta;
	
	private Integer fc;
	
	private Integer fr;
	
	private Integer t;

	public TensionAbdominal getTa() {
		return ta;
	}

	public void setTa(TensionAbdominal ta) {
		this.ta = ta;
	}

	public Integer getFc() {
		return fc;
	}

	public void setFc(Integer fc) {
		this.fc = fc;
	}

	public Integer getFr() {
		return fr;
	}

	public void setFr(Integer fr) {
		this.fr = fr;
	}

	public Integer getT() {
		return t;
	}

	public void setT(Integer t) {
		this.t = t;
	}

}
