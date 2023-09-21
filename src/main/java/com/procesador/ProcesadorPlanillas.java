package com.procesador;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.procesador.entities.Empleado;
import com.procesador.proveedor.ProveedorMiembrosPlanilla;
import com.procesador.util.Util;

public class ProcesadorPlanillas {

	@Autowired
	private ProveedorMiembrosPlanilla proveedor;

	public ProcesadorPlanillas(ProveedorMiembrosPlanilla proveedor) {
		this.proveedor = proveedor;
	}
	
	
	public double obtenerMontoTotal() {
		List<Empleado> empleados=proveedor.obtenerListaEmpleados();
		
		return empleados.stream()
				         .filter(x->x.isActive() )
				         .peek(x->calcularMontoTotal(x))
				         .mapToDouble(x->x.getMontoMensual())
				         .sum();
	}
	
	public Map<Integer, Float> obtenerMontoPorEmpleado() {
		List<Empleado> empleados=proveedor.obtenerListaEmpleados();
		
		return empleados.stream()
		         .collect(Collectors.toMap(
		        		 Empleado::getID, 
		        		 ProcesadorPlanillas::calcularMontoTotal,
		        		 Float::sum));

	}
	
	private static float calcularMontoTotal(Empleado empleado) {
		Util.validarEmpleado(empleado);
		
		return empleado.isActive()? empleado.getMontoMensual():0;
	}
	

	
}
