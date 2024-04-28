package com.huyle.ms.command;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandGateway {

    private final ApplicationContext context;

    private final Map<Class<?>, CommandHandler<? extends Command>> handlerMapper = new HashMap<>();

    private static final String HANDLE_METHOD = "handle";

    public CommandGateway(ApplicationContext context) {
        this.context = context;
    }

    public void init() {
        List<CommandHandler<? extends Command>> commandHandlers = findAllCommandHandlerBeans();
        addHandlersToMapper(commandHandlers);
    }

    @SuppressWarnings("unchecked")
    private List<CommandHandler<? extends Command>> findAllCommandHandlerBeans() {
        String[] beans = context.getBeanDefinitionNames();
        return Arrays.stream(beans)
                .map(beanName -> context.getBean(beanName))
                .filter(bean -> CommandHandler.class.isAssignableFrom(bean.getClass()))
                .map(handler -> (CommandHandler<? extends Command>)handler)
                .collect(Collectors.toList());
    }

    private void addHandlersToMapper(List<CommandHandler<? extends Command>> commandHandlers) {
        commandHandlers.forEach(handler -> {
            try {
                Method handleMethod = findHandleMethodForHandler(handler);
                Class<?> commandType = handleMethod.getParameterTypes()[0];
                handlerMapper.put(commandType, handler);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <C extends Command> void handle(C command) {
        CommandHandler<C> handler = (CommandHandler<C>)findHandlerForCommand(command);
        handler.handle(command);
    }

    private CommandHandler<? extends Command> findHandlerForCommand(Command command) {
        return handlerMapper.get(command.getClass());
    }

    private Method findHandleMethodForHandler(CommandHandler<? extends Command> handler) {
        Method[] methods = ClassUtils.getUserClass(handler.getClass()).getDeclaredMethods();
        for (var method : methods) {
            if (method.getName().equals(HANDLE_METHOD) && !method.getParameterTypes()[0].equals(Command.class)) {
                return method;
            }
        }
        return null;
    }
}
