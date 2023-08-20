package com.neoris.turnosrotativos.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.services.ConceptoService;

@Service
public class ConceptoServiceImpl implements ConceptoService{
	
	@Autowired
	ConceptoRepository repositorioConcepto;
	
	@Override
	public List<Concepto> obtenerConceptos() {
		
		return repositorioConcepto.findAll();
	}

}
