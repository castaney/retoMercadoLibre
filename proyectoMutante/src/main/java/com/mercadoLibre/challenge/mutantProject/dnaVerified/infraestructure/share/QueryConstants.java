package com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.share;

public class QueryConstants {
	
	
	public static final String QUERY_COUNT_MUTANT_NO_MUTANT="SELECT (SELECT COUNT(*) FROM pru_verificacion_adn WHERE es_mutante='N'), (SELECT COUNT(*) FROM pru_verificacion_adn WHERE es_mutante='Y')FROM DUAL";

}
