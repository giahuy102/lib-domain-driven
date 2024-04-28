package com.huyle.ms.config;

import com.huyle.ms.command.CommandGateway;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class EventListener implements ApplicationListener<ContextRefreshedEvent> {

    private final CommandGateway commandGateway;

    public EventListener(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Call init method after all beans are registered in application context -> avoid cyclic dependency between beans
        commandGateway.init();
    }
}
