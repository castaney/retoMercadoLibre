package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus;
/**
 * Bus que transporta informacion y hace llamado al adpatador que se
 * encuentra en la capa de infraestructura para ejecutar un evento
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 */
public interface CommandBus {
	
	Boolean handle(Command command) throws Exception;

}
