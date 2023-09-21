package com.procesador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.procesador.entities.Empleado;
import com.procesador.proveedor.ProveedorMiembrosPlanilla;
import com.procesador.util.Util;

public class ProcesadorPlanillasEstructurado {

	@Autowired
	ProveedorMiembrosPlanilla proveedor;

	public ProcesadorPlanillasEstructurado(ProveedorMiembrosPlanilla proveedor) {
		this.proveedor = proveedor;
	}

	public float obtenerMontoTotal() {
		float montoTotal = 0;
		
		List<Empleado> empleados = proveedor.obtenerListaEmpleados();
		
		for (Empleado empleado : empleados) {
			if(empleado.isActive()) {
				Util.validarEmpleado(empleado);
			    montoTotal += empleado.getMontoMensual();
			}
		}
		return montoTotal;
	}
	
	public Map<Integer,Float> obtenerMontoPorEmpleado() {
		List<Empleado> empleados = proveedor.obtenerListaEmpleados();
		
		Map<Integer,Float> montoTotalPorEmpleado= new HashMap<Integer,Float>();
		for (Empleado empleado : empleados) {
			if(empleado.isActive()) {
				Util.validarEmpleado(empleado);
				int idEmpleado = empleado.getID();
                float montoMensual = empleado.getMontoMensual();
                
				if(montoTotalPorEmpleado.containsKey(idEmpleado)) {
					float montoExistente=montoTotalPorEmpleado.get(idEmpleado);
					float nuevoMonto=montoMensual+montoExistente;
					montoTotalPorEmpleado.put(idEmpleado, nuevoMonto);
				}else {
				montoTotalPorEmpleado.put(idEmpleado, montoMensual);
				}
			}
		}
		
		return montoTotalPorEmpleado;
	}

}
