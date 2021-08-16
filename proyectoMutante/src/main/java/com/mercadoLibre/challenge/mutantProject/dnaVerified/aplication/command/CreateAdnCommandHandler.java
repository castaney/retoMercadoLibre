package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.command;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus.CommandHandler;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.Dna;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.shared.domainDnaBus.DnaValidation;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.shared.domainDnaBus.DomainDnaBus;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.DnaJPARepository;

@Component
public class CreateAdnCommandHandler implements CommandHandler<CreateAdnCommand> {
	private DnaJPARepository dnaRepository;

	private Map<Class,DomainDnaBus> domainDnaBusMap;
	

	public CreateAdnCommandHandler(DnaJPARepository dnaRepository, List<DomainDnaBus> domainDnaBuses) {
		this.domainDnaBusMap = new HashMap<>();
		domainDnaBuses.forEach(domainHandler -> {
			Class<?> commandClass = getCommandClass(domainHandler);
			domainDnaBusMap.put(commandClass, domainHandler);
		});
		this.dnaRepository = dnaRepository;
		
	}

	@Override
	public Boolean handle(CreateAdnCommand command) throws Exception {
		
		if(command== null || command.getAdnList()==null || command.getAdnList().isEmpty()) {
			throw new Exception();
		}
	    List<String> stringBackList= command.getAdnList().stream().collect(Collectors.toList());
		Boolean isMutant=domainDnaBusMap.get(DomainDnaBus.class).isMutant(command.getAdnList());
		ObjectMapper obj= new ObjectMapper();
		String dnaStr="";
		
		dnaStr=obj.writeValueAsString(stringBackList);
		
		
		Dna dna= new Dna(dnaStr, isMutant);
		
		dnaRepository.save(dna);
		
		
		
		return Boolean.TRUE;
		
	}
	
	private Class<?> getCommandClass(DomainDnaBus domainDnaBus) {
		Type commandInterface =  domainDnaBus.getClass().getGenericInterfaces()[0];
		return getClass(commandInterface.getTypeName());
	}

	private Class<?> getClass(String name) {
		try {
			
			return Class.forName(name);
		} catch (Exception e) {
			return null;
		}
	}

}
