package com.mercadoLibre.challenge.mutantProject.dnaVerified.infraestructure.spring;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus.Command;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus.CommandBus;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.aplication.commandBus.CommandHandler;

@Component
@Primary
public class SpringCommandBus implements CommandBus {
	private Map<Class, CommandHandler<Command>> handlers;

	public SpringCommandBus(List<CommandHandler> commandHandlerImplementations) {
		this.handlers = new HashMap<>();
		commandHandlerImplementations.forEach(commandHandler -> {
			Class<?> commandClass = getCommandClass(commandHandler);
			handlers.put(commandClass, commandHandler);
		});
	}

	@Override
	public Boolean handle(Command command) throws Exception {
	    if (!handlers.containsKey(command.getClass())) {
            throw new Exception(String.format("No handler for %s", command.getClass().getName()));
        }
       Boolean isMutant= handlers.get(command.getClass()).handle(command);
       
       return isMutant;

	}

	private Class<?> getCommandClass(CommandHandler handler) {
		Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0])
				.getActualTypeArguments()[0];
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
