package com.huyle.ms.domain;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public abstract class DomainEvent {
    protected Date createdAt;
    protected UUID eventId;

    public DomainEvent() {
        this.createdAt = new Date();
        this.eventId = UUID.randomUUID();
    }
}
