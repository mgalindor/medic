package com.mk.mnx.mdc.model.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mk.mnx.infr.model.BaseModel;

@Document(collection = "usuarios")
public class Catalogo extends BaseModel{

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String nombre;
	
	private String descripcion;
	
	private List<ElementoC> elementos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ElementoC> getElementos() {
		return elementos;
	}

	public void setElementos(List<ElementoC> elementos) {
		this.elementos = elementos;
	}
	
	
}
