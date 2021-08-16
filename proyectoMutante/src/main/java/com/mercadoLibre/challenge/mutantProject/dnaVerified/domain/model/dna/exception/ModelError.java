package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception;

public class ModelError {
	  private String message;
	    private String codigo;
	    private Object valor;

	    public ModelError(String message, String codigo, Object valor) {
	        this.message = message;
	        this.setCodigo(codigo);
	         this.setValor(valor);
	    }

	    @Override
	    public String toString() {
	        return "{" +
	                "message='" + message + "'\'"+" valor recibido:"+valor+ '\'' +
	                '}';
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

		/**
		 * @return the codigo
		 */
		public String getCodigo() {
			return codigo;
		}

		/**
		 * @param codigo the codigo to set
		 */
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

	

		/**
		 * @return the valor
		 */
		public Object getValor() {
			return valor;
		}

		/**
		 * @param valor the valor to set
		 */
		public void setValor(Object valor) {
			this.valor = valor;
		}

}
