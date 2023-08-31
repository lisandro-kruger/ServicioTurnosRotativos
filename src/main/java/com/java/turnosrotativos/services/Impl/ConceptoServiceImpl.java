package com.java.turnosrotativos.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.turnosrotativos.entities.Concepto;
import com.java.turnosrotativos.repositories.ConceptoRepository;
import com.java.turnosrotativos.services.ConceptoService;

@Service
public class ConceptoServiceImpl implements ConceptoService{
	
	@Autowired
	ConceptoRepository repositorioConcepto;
	
	@Override
	public List<Concepto> obtenerConceptos() {
		
		return repositorioConcepto.findAll();
	}

}
