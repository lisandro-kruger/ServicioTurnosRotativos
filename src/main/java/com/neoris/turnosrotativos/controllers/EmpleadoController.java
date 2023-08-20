package com.neoris.turnosrotativos.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.requests.EmpleadoRequest;
import com.neoris.turnosrotativos.services.Impl.EmpleadoServiceImpl;

@RestController
public class EmpleadoController {

	@Autowired
	EmpleadoServiceImpl servicioEmpleado;

	/*
	 * ANTES DE DAR DE ALTA UN EMPLEADO VALIDAMOS LO REQUERIMIENTOS DE LA HU Y LUEGO
	 * MOSTRAMOS UN MENSAJE CON EL OBJETO PERSISTIDO.
	 */
	@PostMapping("/empleado")
	public ResponseEntity<String> altaEmpleado(@Valid @RequestBody EmpleadoRequest empleado) {

		if (ValidaredadEmpleado(empleado.getFechaNacimiento()) < 18) {
			// SI UN EMPLEADO ES MENOR DE EDAD MUESTRA EL MENSAJE Y RETORNA UN 400.
			return new ResponseEntity<>("'La edad del empleado no puede ser menor a 18 años.'", HttpStatus.BAD_REQUEST);
		} else {
			// CREAMOS UN OBJETO EMPLEADO.
			Empleado newEmpleado = empleado.toEmpleado();
			// LLAMOS AL SERVICIO EMPLEADO.
			servicioEmpleado.guardarEmpleado(newEmpleado);
			// SI NO EXITEN CONFICTOS RETORNA EL EMPLEADO PERSISTIDO.
			return new ResponseEntity<>(newEmpleado.toString(), HttpStatus.CREATED);
		}

	}

	// OBTENEMOS LA EDAD DE LA PERSONA
	private int ValidaredadEmpleado(LocalDate fechaNacimiento) {

		LocalDate fechaActual = LocalDate.now();
		/*
		 * TOMA DOS OBJETOS LOCALDATE COMO ARGUMENTOS Y DEVUELVE UN OBJETO PERIOD QUE
		 * REPRESENTA LA DIFERENCIA ENTRE AMBAS FECHAS EN AÑOS, MESES Y DIAS. LUEGO,
		 * LLAMAMOS AL MÉTODO GETYEARS() DEL OBJETO PERIOD PARA OBTENER SOLO LA
		 * DIFERENCIA EN AÑOS.
		 */
		return Period.between(fechaNacimiento, fechaActual).getYears();

	}

	// OBTENEMOS LA INFORMACION DE TODOS LOS EMPLEADOS DE LA BASE DE DATOS.
	@GetMapping("/empleado")
	public ResponseEntity<List<Empleado>> obtenerEmpleados() {

		List<Empleado> empleadoList = servicioEmpleado.obtenerEmpleados();
		return new ResponseEntity<>(empleadoList, HttpStatus.OK);
	}

	// OBTENEMS LA INFORMACION DE UN EMPLEADO PASANDO EL ID EN LA URL.
	@GetMapping("/empleado/{empleadoId}")
	public ResponseEntity<String> obtenerEmpleado(@PathVariable("empleadoId") Long id) {

		Optional<Empleado> empleado = servicioEmpleado.obtenerEmpleado(id);

		if (empleado.isPresent()) {
			// SI EL OBJETO BUSCADO POR ID ESTA PRESENTE MUESTRA SU INFORMACION.
			EmpleadoRequest response = new EmpleadoRequest().toEmpleadoResponse(empleado);
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		} else {
			// SI UN EMPLEADO CON ESE ID NO EXISTE DEVUELVE UN MENSAJE DE NO ENCONTRADO.
			return new ResponseEntity<>("No se encontró el empleado con Id: " + id, HttpStatus.NOT_FOUND);
		}
	}

	// PASAMOS EL ID EN LA URL PARA ELIMINAR UN EMPLEADO.
	@DeleteMapping("/empleado/{empleadoId}")
	public ResponseEntity<String> eliminarEmpleado(@PathVariable("empleadoId") Long id) {

		Optional<Empleado> empleado = servicioEmpleado.obtenerEmpleado(id);

		if (empleado.isPresent()) {
			// SI ESTA PRESENTE LLAMA AL SERVICIO PARA PODER ELIMINARLO.
			servicioEmpleado.eliminarEmpleado(empleado.get());
			// PARA MOSTRAR EL MENSAJE DE CONFIRMACION SE DEBERIA CAMBIAR EL ESTATUS HTTP.
			System.out.println("El empleado fue eliminado con éxito.");
			return new ResponseEntity<>("El empleado fue eliminado con éxito.", HttpStatus.NO_CONTENT);
		} else {
			// SI NO EXISTE EL EMPLEADO MUESTRA UN MENSAJE DE NO ENCONTRADO.
			return new ResponseEntity<>("No se encontró el empleado con Id: " + id, HttpStatus.NOT_FOUND);
		}
	}
}
