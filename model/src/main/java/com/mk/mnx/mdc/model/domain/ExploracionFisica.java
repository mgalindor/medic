package com.mk.mnx.mdc.model.domain;

import com.mk.mnx.infr.model.BaseModel;

public class ExploracionFisica extends BaseModel {
	
	private String habitusExterior;
	private String cabeza;
	private String cuello;
	private String torax;
	private String abdomen;
	private String extremidades;
	public String getHabitusExterior() {
		return habitusExterior;
	}
	public void setHabitusExterior(String habitusExterior) {
		this.habitusExterior = habitusExterior;
	}
	public String getCabeza() {
		return cabeza;
	}
	public void setCabeza(String cabeza) {
		this.cabeza = cabeza;
	}
	public String getCuello() {
		return cuello;
	}
	public void setCuello(String cuello) {
		this.cuello = cuello;
	}
	public String getTorax() {
		return torax;
	}
	public void setTorax(String torax) {
		this.torax = torax;
	}
	public String getAbdomen() {
		return abdomen;
	}
	public void setAbdomen(String abdomen) {
		this.abdomen = abdomen;
	}
	public String getExtremidades() {
		return extremidades;
	}
	public void setExtremidades(String extremidades) {
		this.extremidades = extremidades;
	}

	

}
