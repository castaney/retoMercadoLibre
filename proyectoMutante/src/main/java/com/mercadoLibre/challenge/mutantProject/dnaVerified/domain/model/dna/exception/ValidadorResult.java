package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidadorResult<T> {
	
	private T ok;
    private boolean validated = false;
    private List<ModelError> errores = new ArrayList<>();
    private String mensaje="";

    public ValidadorResult(T ok) {
        this.ok = ok;
    }

    public ValidadorResult(ModelError error) {
    	this.mensaje=error.getMessage();
        this.errores.add(error);
    }

 
    public T ok() throws RuntimeException {
        if (!validated || isError()) {
            throw new RuntimeException("OK no existe. Error");
        }
        return ok;
    }

 
    
  

    public boolean isError() {
        validated = true;
        return this.errores.size() > 0;
    }

    public static <T> ValidadorResult<T> ok(T ok) {
        return new ValidadorResult<T>(ok);
    }


    





    /**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	

	public static ValidadorResult error(ModelError error) {
        return new ValidadorResult(error);
    }

   

 

    

  

}
