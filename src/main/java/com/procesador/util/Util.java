package com.procesador.util;

import com.procesador.entities.Empleado;

public class Util {

	public static  void validarEmpleado(Empleado empleado) {
		if(empleado.getMontoMensual()<0) {
			throw new IllegalArgumentException("El monto mensual no puede ser negativo.");
		}
		
		if (empleado.getID() == 0) {
            throw new IllegalArgumentException("ID no puede ser 0.");
        }

        if (empleado.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío.");
        }
	}
}
