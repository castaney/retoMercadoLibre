package com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.command.CreateAdnCommand;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus.CommandBus;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.DnaStatsQueryDTO;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus.QueryBus;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.share.constants.MessageInfraestructure;


@RestController
@RequestMapping("/mutant")
public class DnaController {

	private QueryBus queryBus;
	
	private CommandBus commandBus;
	
	  public DnaController( CommandBus commandBus, QueryBus queryBus) {
		  this.queryBus=queryBus;
	        this.commandBus = commandBus;
	    }

	  /**
	   * Servicio que verifica si una secuencia de ADN corresponde
	   * a un mutante o no
	   * @param dnaList lista con las cadenas de un ADN
	   * @return true si es mutante
	   */
	@PostMapping
	public ResponseEntity verifyMutant(@RequestBody List<String> dnaList)  {
		 
		if(dnaList==null || dnaList.isEmpty()) {
			return new ResponseEntity<>(MessageInfraestructure.SHOULD_ADD_DNA, HttpStatus.FORBIDDEN);
		}
		CreateAdnCommand command = CreateAdnCommand.Builder.getInstance()
	                .adnList(dnaList)
	                .build();
		 Boolean returnVerify= null;
		  try {
			returnVerify=commandBus.handle(command);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		  
		
		return ResponseEntity.ok(returnVerify);
	}
	
	/**
	 * Metodo qeu permite 
	 * consultar las verificaciones con el fin
	 * de mostrar estadisticas de la informacion
	 * verificada y asi saber cuantos han sido
	 * verificados como mutantes y cuantos como humanos para
	 * ayudar a magneto.
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/stats")
	@ResponseBody
	public ResponseEntity<DnaStatsQueryDTO> getStatsCheck() throws Exception {

		DnaStatsQueryDTO dnaStatistics = queryBus.handle(DnaStatsQueryDTO.class.getCanonicalName());
		if (dnaStatistics == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(dnaStatistics);
	}

}
