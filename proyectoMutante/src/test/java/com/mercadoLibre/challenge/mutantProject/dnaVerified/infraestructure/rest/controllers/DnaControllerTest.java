package com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.rest.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.constants.MessagesModel;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.DnaJPARepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class DnaControllerTest {

//	@Mock
//	private DnaController dnaController;


	@Autowired
	private MockMvc mvc;
	@Autowired
	DnaJPARepository repository;
	
	
    @Test
	public void verifyMutantTest() throws Exception {
		List<String> listStringDNA=  new ArrayList<String>(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
		 ObjectMapper objectMapper= new ObjectMapper();
		MvcResult resultSevice = mvc
				.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(listStringDNA)))
				.andExpect(status().isOk()).andReturn();
		
		int status=200;
		Assertions.assertEquals(status, resultSevice.getResponse().getStatus());
		
		resultSevice = mvc.perform(MockMvcRequestBuilders.get("/mutant/stats").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
  		
  		 String result="{\"countMutantDna\":2,\"countHumanDna\":9,\"ratio\":0.22}";
  		Assertions.assertEquals(result,resultSevice.getResponse().getContentAsString());
		        

	}
    
    @Test
	public void verifyMutantTestEmptyList() throws Exception {
		List<String> listStringDNA=  new ArrayList<String>();
		 ObjectMapper objectMapper= new ObjectMapper();
		MvcResult resultSevice = mvc
				.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(listStringDNA)))
				.andExpect(status().isForbidden()).andReturn();
		
		int status=403;
		Assertions.assertEquals(status, resultSevice.getResponse().getStatus());
		//Borramos todo lo de la bd
	   repository.deleteAll();
	   status=204;
	   resultSevice = mvc.perform(MockMvcRequestBuilders.get("/mutant/stats").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()).andReturn();
	   Assertions.assertEquals(status, resultSevice.getResponse().getStatus());
	   
	   
		        

	}
    
    @Test
	public void verifyStructureFailed() throws Exception {
    	
    	List<String> listStringDNA=  new ArrayList<String>(Arrays.asList("AxGCGA","CGTGC","TTATGT","AGAAGG","CDCCTA","TCACTG"));
		 ObjectMapper objectMapper= new ObjectMapper();
		MvcResult resultSevice = mvc
				.perform(MockMvcRequestBuilders.post("/mutant").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(listStringDNA)))
				.andExpect(status().isForbidden()).andReturn();
		
	
		Assertions.assertEquals(MessagesModel.STRING_FAIL_IN_STRUCTURE,
				resultSevice.getResponse().getContentAsString());
		
		
		

		        

	}
  

}
