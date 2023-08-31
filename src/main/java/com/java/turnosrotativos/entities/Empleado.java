package com.java.turnosrotativos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nro_documento")
	private Long nroDocumento;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	private String email;

	@Column(name = "fecha_nacimiento", nullable = false)
	private String fechaNacimiento;

	@Column(name = "fecha_ingreso", nullable = false)
	private String fechaIngreso;

	@Column(name = "fecha_creacion")
	private String fechaCreacion;

	// CONTRUCTOR POR DEFECTO

	// GETTERS Y SETTERS
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
