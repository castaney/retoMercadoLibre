package com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.command;

import java.util.List;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus.Command;

public class CreateAdnCommand  extends Command{
	
	  private CreateAdnCommand(List<String> adnList) {
	        this.adnList=adnList;
	    }
	
	private List<String> adnList;

	/**
	 * Permite obener valor de atributo
	 * @return the adnList
	 */
	public List<String> getAdnList() {
		return adnList;
	}

	/**
	 * Permite cambiar el valor del atributo
	 * @param adnList the adnList to set
	 */
	public void setAdnList(List<String> adnList) {
		this.adnList = adnList;
	}
	
	public static class Builder {

        private List<String> adnList;

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder adnList(List<String> adnList) {
            this.adnList = adnList;
            return this;
        }

        public CreateAdnCommand build() {
            return new CreateAdnCommand(adnList);
        }
    }
	
	

}
