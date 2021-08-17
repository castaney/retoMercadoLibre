package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus;

/**
 * Corresponde a manejador de los eventos que se generan
 * desde la capa de infraestructura para saber que instancia
 * debera ser ejecutada como adaptador
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 * @param <T>
 */
public interface QueryHandler <T> {
	
	 T handle() throws Exception;

}
