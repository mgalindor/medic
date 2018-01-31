package com.mk.mnx.mdc.model.domain;

import java.util.Date;

import com.mk.mnx.infr.model.BaseModel;
import com.mk.mnx.mdc.model.states.EnuTipoCambio;

public class FootPrint extends BaseModel {
	
	private String by;
	
	private Date at;
	
	private EnuTipoCambio reason;
	
	public FootPrint () {
	}
	
	public FootPrint (String by,Date at, EnuTipoCambio reason) {
		this.by = by;
		this.at = at;
		this.reason = reason;
	}
	
	public String getBy() {
		return by;
	}
	
	public void setBy(String by) {
		this.by = by;
	}

	public Date getAt() {
		return at;
	}

	public void setAt(Date at) {
		this.at = at;
	}

	public EnuTipoCambio getReason() {
		return reason;
	}
	
	public void setReason(EnuTipoCambio reason) {
		this.reason = reason;
	}
	
}
