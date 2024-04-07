package com.huyle.ms.config;

import com.huyle.ms.command.CommandGateway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(CommandGateway.class)
    public CommandGateway commandGateway() {
        return new CommandGateway();
    }
}
