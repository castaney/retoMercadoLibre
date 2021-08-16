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

    public ValidadorResult(List<ModelError> errores) {
        this.errores.addAll(errores);
    }

    public T ok() throws RuntimeException {
        if (!validated || isError()) {
            throw new RuntimeException("OK no existe. Error");
        }
        return ok;
    }

 
    
    public ValidadorResult  addMensaje(String mensaje) {
    	this.mensaje=mensaje;
    	return this;
    	
    }

    public boolean isError() {
        validated = true;
        return this.errores.size() > 0;
    }

    public static <T> ValidadorResult<T> ok(T ok) {
        return new ValidadorResult<T>(ok);
    }

    public static <T> ValidadorResult<T> ok(ValidadorResult ok) {
        if (ok == null) {
            return new ValidadorResult<T>((T) null);
        } else {
            return new ValidadorResult<T>((T) ok.ok());
        }
    }
    

    public static <T> ValidadorResult<T> okMensaje(ValidadorResult ok) {
        if (ok == null) {
            return new ValidadorResult<T>((T) null);
        } else {
            return ok;
        }
    }

    public static ValidadorResult error(ValidadorResult error) {
        return error(error.getErrores());
    }

    /**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public static ValidadorResult error(ModelError error) {
        return new ValidadorResult(error);
    }

    public static ValidadorResult error(List<ModelError> errores) {
        return new ValidadorResult(errores);
    }

    public List<ModelError> getErrores() {
        return errores;
    }

    public void setErrores(List<ModelError> errores) {
        this.errores = errores;
    }

    @Override
    public String toString() {
        return "Result{" +
                "ok=" + ok +
                ", validated=" + validated +
                ", errores=" + errores +
                '}';
    }

}
