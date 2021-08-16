package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.shared.domainDnaBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.constants.MessagesModel;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception.DnaStructureException;

public class DnaValidationTest {
	@Autowired
	DnaValidation dnaValidation;
	
	@Test
	void  isMutant() throws DnaStructureException {
		dnaValidation= new DnaValidation();
		//entrada list de strings a verficar  “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
		List<String> listStringDNA=  new ArrayList<String>(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
		//test result=1 que indica que encontro 1 coincidencia
		//assert == true
		boolean isMutant=dnaValidation.isMutant(listStringDNA);
		
		Assertions.assertTrue(isMutant );
		
	}
	
	@Test
	void  isHuman() throws DnaStructureException {
		dnaValidation= new DnaValidation();
		//entrada list de strings a verficar  “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
		List<String> listStringDNA=  new ArrayList<String>(Arrays.asList("ATGCGA","CGTGCC","TTATGT","AGAAGG","CTCCTA","TCACTG"));
		//test result=1 que indica que encontro 1 coincidencia
		
		boolean isMutant=dnaValidation.isMutant(listStringDNA);
		//assert == false
		Assertions.assertFalse(isMutant );
		
	}
	
	/**
	 * Se espera que la prueba salte a la excepcion por error en estructura
	 */
	@Test
	void  isValueNoSupport()  {
		dnaValidation= new DnaValidation();
		//entrada list de strings a verficar "AxGCGA","CGTGC","TTATGT","AGAAGG","CDCCTA","TCACTG"
		List<String> listStringDNA=  new ArrayList<String>(Arrays.asList("AxGCGA","CGTGC","TTATGT","AGAAGG","CDCCTA","TCACTG"));
		
	     assertNosupportList(listStringDNA,MessagesModel.STRING_FAIL_IN_STRUCTURE);
	     
	     listStringDNA=  new ArrayList<String>(Arrays.asList("AAAGCGAAA","AAAGCGAA","AAAGCGAA","AAAGCGAA","AAAGCGAA","AAAGCGAA"));
	     assertNosupportList(listStringDNA, MessagesModel.COL_FILE_ERROR);
		
		
	}

	private void assertNosupportList(List<String> listStringDNA, String message) {
		boolean isMutant;
		try {
			isMutant = dnaValidation.isMutant(listStringDNA);
		} catch (DnaStructureException e) {
		 Assertions.assertEquals(message, e.getMessage());
		}
		
	}


}
