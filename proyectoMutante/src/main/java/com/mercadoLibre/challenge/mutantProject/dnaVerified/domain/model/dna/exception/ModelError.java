package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception;

public class ModelError {
	  private String message;


	    public ModelError(String message, String codigo, Object valor) {
	        this.setMessage( message);
	    }

	
	 
	    

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}


	

	

	
}
