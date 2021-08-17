package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus;
/**
 * Corresponde a manejador de los eventos que se generan
 * desde la capa de infraestructura para saber que instancia
 * debera ser ejecutada como adaptador
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 * @param <T>
 */
public interface CommandHandler<T extends Command> {
	
	 Boolean handle(T command) throws Exception;

}
