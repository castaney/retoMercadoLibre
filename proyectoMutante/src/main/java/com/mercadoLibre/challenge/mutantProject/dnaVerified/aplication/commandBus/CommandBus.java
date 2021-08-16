package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus;

public interface CommandBus {
	
	Boolean handle(Command command) throws Exception;

}
