package com.java.turnosrotativos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.turnosrotativos.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	/*
	 * CONSULTA SQL PARA VERIFICAR SI YA EXISTE UN EMPLEADO REGISTRADO CON EL MISMO
	 * NRO DE DECOCUMENTO.
	 */
	@Query(value = "SELECT e.nro_documento FROM empleado e WHERE e.nro_documento = ?1", nativeQuery = true)
	Long findByNumeroDocumento(Long nroDocumento);

	/*
	 * CONSULTA SQL PARA VERIDICAR SI YA EXISTE UN EMPLEADO REGISTRADO CON EL MISMO
	 * EMAIL.
	 */
	@Query(value = "SELECT e.email FROM empleado e WHERE e.email = ?1", nativeQuery = true)
	String findByEmail(String email);

}
