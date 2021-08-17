package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.shared.domainDnaBus;

import java.util.List;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception.DnaStructureException;

/**
 * Bus que transporta informacion y hace llamado al adpatador que se
 * encuentra en la capa de infraestructura para ejecutar un evento
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 */
public interface DomainDnaBus {
	
	boolean isMutant(List<String> listDna) throws DnaStructureException;
	

}
