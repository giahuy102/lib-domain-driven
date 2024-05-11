package com.huyle.ms.config;

import com.huyle.ms.command.CommandGateway;
import com.huyle.ms.domain.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainAutoConfiguration {

    @Autowired
    ApplicationContext context;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Bean
    @ConditionalOnMissingBean(CommandGateway.class)
    public CommandGateway commandGateway() {
        return new CommandGateway(context);
    }

    @Bean
    public EventListener commandGatewayListener(CommandGateway commandGateway) {
        return new EventListener(commandGateway);
    }

    @Bean
    public DomainEventPublisher domainEventPublisher() {
        return new DomainEventPublisher(applicationEventPublisher);
    }
}
