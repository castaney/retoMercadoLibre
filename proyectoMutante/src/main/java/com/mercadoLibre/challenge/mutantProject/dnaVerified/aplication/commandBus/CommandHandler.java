package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus;

public interface CommandHandler<T extends Command> {
	
	 Boolean handle(T command) throws Exception;

}
