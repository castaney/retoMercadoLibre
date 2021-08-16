package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.shared.domainDnaBus;

import java.util.List;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception.DnaStructureException;

public interface DomainDnaBus {
	
	boolean isMutant(List<String> listDna) throws DnaStructureException;
	

}
