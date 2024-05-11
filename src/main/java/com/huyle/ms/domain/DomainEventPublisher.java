package com.huyle.ms.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class DomainEventPublisher {
    private List<Object> domainEvents = new ArrayList<>();

    private ApplicationEventPublisher applicationEventPublisher;

    public DomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void registerEvent(DomainEvent event) {
        Assert.notNull(event, "Domain event must not be null");
        this.domainEvents.add(event);
    }

    public void publishEvents() {
        this.domainEvents.forEach(event -> {
            applicationEventPublisher.publishEvent(event);
        });
        clearDomainEvents();
    }

    private void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
