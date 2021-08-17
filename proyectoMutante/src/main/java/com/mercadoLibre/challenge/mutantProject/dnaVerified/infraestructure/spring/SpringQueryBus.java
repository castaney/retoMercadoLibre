package com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.spring;

import java.lang.reflect.ParameterizedType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus.Query;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus.QueryBus;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.query.querybus.QueryHandler;
/**
 * Clase encargada de ejecutar los consultas y llamar a los casos de uso
 * correspondientes
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 */
@Component
@Primary
public class SpringQueryBus implements QueryBus {
	private Map<Class, QueryHandler<Query>> handlers;
	
	public SpringQueryBus(List<QueryHandler> commandHandlerImplementations) {
		this.handlers = new HashMap<>();
		commandHandlerImplementations.forEach(commandHandler -> {
			Class<?> commandClass = getQueryClass(commandHandler);
			handlers.put(commandClass, commandHandler);
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Query handle(String val) throws Exception {
		Class<?> classF= getClass(val);
		 if (!handlers.containsKey(classF)) {
	            throw new Exception(String.format("No handler for %s", classF.getName()));
	        }
		
		 Query returnValue= handlers.get(classF).handle();
	       return returnValue;
	}
	

    private Class<?> getQueryClass(QueryHandler handler) {
        Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
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
