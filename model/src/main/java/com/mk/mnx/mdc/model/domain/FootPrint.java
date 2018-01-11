package com.mk.mnx.mdc.model.domain;

import java.util.Date;

import com.mk.mnx.infr.model.BaseModel;

public class FootPrint extends BaseModel {
	
	private String by;
	
	private Date at;
	
	public FootPrint () {
	}
	
	public FootPrint (String by,Date at) {
		this.by = by;
		this.at = at;
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

	
}
