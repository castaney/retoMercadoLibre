/**
 * 
 */
package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Entidad que contiene la informacion del adn y su
 * correspondiente verificacion que determina si es o no mutante
 * la cual representa la informacion de la tabla en base de datos
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 */
@Entity
@Table(name="pru_verificacionAdn")
public class Dna implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	public Dna( String adn, Boolean esMutante) {
	
		this.adn = adn;
		this.esMutante = esMutante;
	}
	
	
	public Dna() {}


	/**Identificador de la entidad*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	/**cadena de adn*/
	@Column(nullable=false)
	private String adn;
	/**representa si es o no mutante*/
	@Type(type = "yes_no")
	@Column(nullable=false)
	private Boolean esMutante;
	/**
	 * Permite obener valor de atributo
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Permite cambiar el valor del atributo
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Permite obener valor de atributo
	 * @return the adn
	 */
	public String getAdn() {
		return adn;
	}
	/**
	 * Permite cambiar el valor del atributo
	 * @param adn the adn to set
	 */
	public void setAdn(String adn) {
		this.adn = adn;
	}
	/**
	 * Permite obener valor de atributo
	 * @return the esMutante
	 */
	public Boolean getEsMutante() {
		return esMutante;
	}
	/**
	 * Permite cambiar el valor del atributo
	 * @param esMutante the esMutante to set
	 */
	public void setEsMutante(Boolean esMutante) {
		this.esMutante = esMutante;
	}
	
	
	

}
