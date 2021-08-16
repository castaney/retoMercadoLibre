package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus;

public interface QueryHandler <T> {
	
	 T handle() throws Exception;

}
