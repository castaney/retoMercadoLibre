package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus;

public interface QueryBus {
	
	<T>T handle(String val) throws Exception;

}
