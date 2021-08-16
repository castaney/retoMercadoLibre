package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query;

import java.io.Serializable;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus.Query;


public class DnaStatsQueryDTO extends Query implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long countMutantDna;
	private Long countHumanDna;
	private Double ratio;
	
	
	public DnaStatsQueryDTO(Long countMutantDna, Long countHumanDna, Double ratio) {
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio=ratio;
		
	}
	/**
	 * Permite obener valor de atributo
	 * @return the countMutantDna
	 */
	public Long getCountMutantDna() {
		return countMutantDna;
	}
	/**
	 * Permite cambiar el valor del atributo
	 * @param countMutantDna the countMutantDna to set
	 */
	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	/**
	 * Permite obener valor de atributo
	 * @return the countHumanDna
	 */
	public Long getCountHumanDna() {
		return countHumanDna;
	}
	/**
	 * Permite cambiar el valor del atributo
	 * @param countHumanDna the countHumanDna to set
	 */
	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	/**
	 * Permite obener valor de atributo
	 * @return the ratio
	 */
	public Double getRatio() {
		return ratio;
	}
	/**
	 * Permite cambiar el valor del atributo
	 * @param ratio the ratio to set
	 */
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}


	
	
	
	
	

}
