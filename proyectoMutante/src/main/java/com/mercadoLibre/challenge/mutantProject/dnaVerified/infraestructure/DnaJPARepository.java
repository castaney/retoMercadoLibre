package com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.Dna;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.share.QueryConstants;
/**
 * Inteface que permite tener los contratos para un crud usando el CrudRepository de JPA
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 */
public interface DnaJPARepository extends JpaRepository<Dna,Long> {
	
	@Query(value = QueryConstants.QUERY_COUNT_MUTANT_NO_MUTANT,nativeQuery = true)
	List<Object[]>getDataStatsFromDNA();
	List<Dna> findByadn(String adn);
	
		
}
