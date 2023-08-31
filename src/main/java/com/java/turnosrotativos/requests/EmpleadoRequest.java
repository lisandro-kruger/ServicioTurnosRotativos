package com.java.turnosrotativos.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.java.turnosrotativos.entities.Empleado;

@Component
public class EmpleadoRequest {

	// ATRIBUTOS CON ALGUNAS VALIDACIONES ANTES DE PERSISTIR EL OBJETO REQUEST.
	private Long id;

	private Long nroDocumento;

	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Solo se permiten letras en el campo 'nombre'")
	@NotNull(message = "‘nombre’ es obligatirio.")
	private String nombre;

	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Solo se permiten letras en el campo 'apellido'")
	@NotNull(message = "‘apellido’ es obligatirio.")
	private String apellido;

	@Email(message = "'El email ingresado no es correcto.'")
	@NotNull(message = "‘email’ es obligatirio.")
	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "'La fecha de nacimiento no puede ser posterior al día de la fecha.'")
	@NotNull(message = "‘fecha de nacimiento’ es obligatirio.")
	private LocalDate fechaNacimiento;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "'La fecha de ingreso no puede ser posterior al día de la fecha.'")
	@NotNull(message = "‘fecha de ingreso’ es obligatirio.")
	private LocalDate fechaIngreso;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCreacion;

	// CONTRUCTOR POR DEFECTO.

	// GETTERS Y SETTERS.
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * toEmpleado ME PERMITE PASARLE LOS DATOS DEL OBJETO EMPLEADO_REQUEST A UN
	 * OBJETO EMPLEADO.
	 * 
	 * @return UN NUEVO OBJETO EMPLEADO
	 */
	public Empleado toEmpleado() {

		// FORMATO DE FECHA.
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Empleado newEmpleado = new Empleado();
		newEmpleado.setNroDocumento(this.nroDocumento);
		newEmpleado.setNombre(this.nombre);
		newEmpleado.setApellido(this.apellido);
		newEmpleado.setEmail(email);
		newEmpleado.setFechaNacimiento(this.fechaNacimiento.format(formato));
		newEmpleado.setFechaIngreso(this.fechaIngreso.format(formato));
		// FECHA ACTUAL
		newEmpleado.setFechaCreacion(this.fechaDeCreacion());

		return newEmpleado;
	}

	/**
	 * fechaDeCreacion ME PERMITE OBTENER LA FECHA ACTUAL PARA LUEGO GUARDARLA EN EL
	 * METODO setFechaCreacion DE EMPLEADO.
	 * 
	 * @return UN STRING CON LA FECHA_ACTUAL CON EL FORMATO YYYY-MM-DD
	 */
	private String fechaDeCreacion() {

		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return fecha.format(formato);
	}

	/**
	 * toEmpleadoRequest ME PERMITE PASARLE LOS DATOS DEL OBJETO_OPTIONAL EMPLEADO A
	 * UN OBJETO EMPLEADO_REQUEST PARA MOSTRARLO EN PANTALLA UNA VEZ ECHA LA
	 * PETICION.
	 * 
	 * @return UN OBJETO EMPLEADO_REQUEST
	 */
	public EmpleadoRequest toEmpleadoResponse(Optional<Empleado> empleado) {

		// FORMATO DE FECHA.
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		EmpleadoRequest responseEmpleado = new EmpleadoRequest();
		responseEmpleado.setId(empleado.get().getId());
		responseEmpleado.setNroDocumento(empleado.get().getNroDocumento());
		responseEmpleado.setNombre(empleado.get().getNombre());
		responseEmpleado.setApellido(empleado.get().getApellido());
		responseEmpleado.setEmail(empleado.get().getEmail());
		// PASAMOS DE STRING A LOCALDATE.
		responseEmpleado.setFechaNacimiento(LocalDate.parse(empleado.get().getFechaNacimiento(), formato));
		responseEmpleado.setFechaIngreso(LocalDate.parse(empleado.get().getFechaIngreso(), formato));
		responseEmpleado.setFechaCreacion(LocalDate.parse(empleado.get().getFechaCreacion(), formato));

		return responseEmpleado;
	}

	// SOBREESCRIBIMOS EL METODO TOSTRING
	@Override
	public String toString() {
		return "{ \n" + "id : " + id + ", \n" + "nroDocumento : " + nroDocumento + ", \n" + "nombre : " + nombre
				+ ", \n" + "apellido : " + apellido + ", \n" + "email : " + email + ", \n" + "fechaNacimiento : "
				+ fechaNacimiento + ", \n" + "fechaIngreso : " + fechaIngreso + ", \n" + "fechaCreacion : "
				+ fechaCreacion + "\n" + "}";
	}

}
