package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus.QueryHandler;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.DnaJPARepository;

@Component
public class DnaStatsQueryHandler implements QueryHandler<DnaStatsQueryDTO>  {
	
	 private DnaJPARepository dnaRepository;
	 
	 

	public DnaStatsQueryHandler(DnaJPARepository dnaRepository) {
		this.dnaRepository = dnaRepository;
	}



	@Override
	public DnaStatsQueryDTO handle() throws Exception {
		List<Object[]> dnasObject= dnaRepository.getDataStatsFromDNA();
		
		Number countHuman=0L;
		Number countMutant=0L;
		if(dnasObject!=null && !dnasObject.isEmpty()) {
			countHuman=(Number) dnasObject.get(0)[0];
			countMutant=(Number) dnasObject.get(0)[1];
		}
		
		Double valResult= countMutant!=null && countHuman!=null && countHuman.doubleValue()>0? countMutant.longValue()/countHuman.doubleValue():0.0;
		DnaStatsQueryDTO dnaDTO=null;
		if(valResult!=null && valResult>0) {
		 BigDecimal valBig= new BigDecimal(valResult).setScale(2, RoundingMode.HALF_DOWN);
			dnaDTO = new DnaStatsQueryDTO(countMutant != null ? countMutant.longValue() : 0,
					countHuman != null ? countHuman.longValue() : 0, valBig != null ? valBig.doubleValue() : 0.0);
		}
		
		
		return dnaDTO;
	}

}
