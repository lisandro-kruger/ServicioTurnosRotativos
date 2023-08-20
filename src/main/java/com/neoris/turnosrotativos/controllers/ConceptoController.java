package com.neoris.turnosrotativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.services.Impl.ConceptoServiceImpl;

@RestController
public class ConceptoController {

	@Autowired
	ConceptoServiceImpl servicioConcepto;

	// OBTENEMOS LA LISTA DE CONCEPTOS
	@GetMapping("/concepto")
	public ResponseEntity<List<Concepto>> obtenerConceptos() {

		List<Concepto> conceptoList = servicioConcepto.obtenerConceptos();
		return new ResponseEntity<>(conceptoList, HttpStatus.OK);
	}
}
