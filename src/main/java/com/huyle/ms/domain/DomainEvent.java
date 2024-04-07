package com.huyle.ms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class DomainEvent<T extends AggregateRoot<? extends Serializable>> {
    private Date createdAt;
    private UUID eventId;
    private T payload;

    public DomainEvent(T payload) {
        this.createdAt = new Date();
        this.eventId = UUID.randomUUID();
        this.payload = payload;
    }
}
