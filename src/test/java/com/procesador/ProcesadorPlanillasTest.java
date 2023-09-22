package com.procesador;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.procesador.entities.Empleado;
import com.procesador.proveedor.ProveedorMiembrosPlanilla;

@SpringBootTest
public class ProcesadorPlanillasTest {

	ProveedorMiembrosPlanilla proveedor = Mockito.mock(ProveedorMiembrosPlanilla.class);

	ProcesadorPlanillas procesador = new ProcesadorPlanillas(proveedor);

	@Test
	public void obtenerMontoTotalEscenarioExitosoTest() {

		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(1, "juan", 100, true));
		empleados.add(new Empleado(2, "alberto", 100, true));
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		double monto=procesador.obtenerMontoTotal();
		Assert.assertEquals(200, monto,0);
	}
	
	@Test
	public void obtenerMontoTotalNegativoTest() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(1, "juan", -1, true));
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		Exception exception = assertThrows(IllegalArgumentException.class,()->{
			procesador.obtenerMontoTotal();
		});
		assertEquals("El monto mensual no puede ser negativo.", exception.getMessage());
	}
	
	@Test
	public void obtenerMontoTotalID0Test() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(0, "juan", 655, true));
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		Exception exception = assertThrows(IllegalArgumentException.class,()->{
			procesador.obtenerMontoTotal();
		});
		assertEquals("ID no puede ser 0.", exception.getMessage());
	}
	
	@Test
	public void obtenerMontoTotalNombreVacioTest() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(1, "", 655, true));
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		Exception exception = assertThrows(IllegalArgumentException.class,()->{
			procesador.obtenerMontoTotal();
		});
		assertEquals("Nombre no puede estar vacío.", exception.getMessage());
	}
	
	
	
	//------------Test por empleados------///
	@Test
	public void obtenerMontoPorEmpleadoEscenarioExitosoTest() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(1, "juan", 100, true));
		empleados.add(new Empleado(2, "alberto", 100, true));
		empleados.add(new Empleado(1, "alberto", 200, true));
		
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		Map<Integer, Float> montoEmpleado=procesador.obtenerMontoPorEmpleado();
		float number=montoEmpleado.get(1);
		Assert.assertEquals(300, number,0);
		
	}
	
	@Test
	public void obtenerMontoPorEmpleadoMontoMensualNegativoTest() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(1, "juan", -1, true));
		empleados.add(new Empleado(2, "alberto", 100, true));
		
		
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		Exception exception = assertThrows(IllegalArgumentException.class,()->{
			procesador.obtenerMontoPorEmpleado();
		});
		assertEquals("El monto mensual no puede ser negativo.", exception.getMessage());
	}
	
	@Test
	public void obtenerMontoPorEmpleadoID0Test() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(0, "juan", 451, true));
		
		
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		Exception exception = assertThrows(IllegalArgumentException.class,()->{
			procesador.obtenerMontoPorEmpleado();
		});
		assertEquals("ID no puede ser 0.", exception.getMessage());
	}
	
	@Test
	public void obtenerMontoPorEmpleadoNombreVacioTest() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(new Empleado(1, "", 451, true));
		
		
		when(proveedor.obtenerListaEmpleados()).thenReturn(empleados);
		Exception exception = assertThrows(IllegalArgumentException.class,()->{
			procesador.obtenerMontoPorEmpleado();
		});
		assertEquals("Nombre no puede estar vacío.", exception.getMessage());
	}
}
