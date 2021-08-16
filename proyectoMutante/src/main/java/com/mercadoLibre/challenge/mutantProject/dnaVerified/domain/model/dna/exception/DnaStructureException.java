package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception;

public class DnaStructureException extends Exception {
	

	private static final long serialVersionUID = 1L;

	public DnaStructureException(String message) {
		super(message);
	}
	public DnaStructureException(String message, Throwable cause) {
		super(message,cause);
	}

}
