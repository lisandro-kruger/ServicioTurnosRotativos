package com.neoris.turnosrotativos.services;

import java.util.List;

import com.neoris.turnosrotativos.entities.Concepto;

public interface ConceptoService {

	// OBTENER LA LISTA DE CONCEPTOS.
	List<Concepto> obtenerConceptos();
}
