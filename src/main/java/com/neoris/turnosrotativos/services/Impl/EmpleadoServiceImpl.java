package com.neoris.turnosrotativos.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.services.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	EmpleadoRepository repositorioEmpleado;

	@Override
	public Empleado guardarEmpleado(Empleado empleado) throws BussinessException {

		// VALIDAR SI EXISTE UN EMPLEADO REGISTRADO CON EL MISMO NUMERO DE DOCUMENTO.
		if (dniEmpleadoExiste(empleado.getNroDocumento())) {
			throw new BussinessException("Ya existe un empleado con el documento ingresado.");
		}

		// VALIDAR SI EXISTE UN EMPLEADO REGISTRADO CON EL MISMO EMAIL.
		if (emailEmpleadoExiste(empleado.getEmail())) {
			throw new BussinessException("Ya existe un empleado con el email ingresado.");
		}

		// SI NO EXISTE CONFLICTOS DAMOS DE ALTA UN EMPLEADO.
		return repositorioEmpleado.save(empleado);
	}

	/**
	 * @param nroDocumento
	 * @return DEVUELVE TRUE SI YA EXISTE UN EMPLEADO CON EL MISMO NRO DE DOCUMENTO
	 *         SINO RETORNA FALSE.
	 */
	private boolean dniEmpleadoExiste(Long nroDocumento) {

		boolean band = false;
		Long dniEmpleado = repositorioEmpleado.findByNumeroDocumento(nroDocumento);

		if (dniEmpleado != null)
			band = true;

		return band;
	}

	/**
	 * 
	 * @param email
	 * @return DEVUELVE TRUE SI YA EXISTE UN EMPLEADO CON EL MISMO NRO DE DOCUMENTO
	 *         SINO RETORNA FALSE.
	 */
	private boolean emailEmpleadoExiste(String email) {

		boolean band = false;
		String emailEmpleado = repositorioEmpleado.findByEmail(email);

		if (emailEmpleado != null)
			band = true;

		return band;
	}

	@Override
	public List<Empleado> obtenerEmpleados() {

		return repositorioEmpleado.findAll();
	}

	@Override
	public Optional<Empleado> obtenerEmpleado(Long id) {

		return repositorioEmpleado.findById(id);
	}

	@Override
	public void eliminarEmpleado(Empleado empleado) {

		repositorioEmpleado.delete(empleado);
	}
}
