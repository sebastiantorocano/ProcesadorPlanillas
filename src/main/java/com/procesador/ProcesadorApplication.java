package com.procesador;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.procesador.proveedor.ProveedorMiembrosPlanilla;

@SpringBootApplication
public class ProcesadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcesadorApplication.class, args);

	}

}
 