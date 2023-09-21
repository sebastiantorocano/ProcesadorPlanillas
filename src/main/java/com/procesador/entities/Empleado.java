package com.procesador.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Empleado {

	private int ID;
	private String nombre;
	private float montoMensual;
	private boolean active;
	
	public Empleado(int iD, String nombre, float montoMensual, boolean active) {
		this.ID = iD;
		this.nombre = nombre;
		this.montoMensual = montoMensual;
		this.active = active;
	}

}
