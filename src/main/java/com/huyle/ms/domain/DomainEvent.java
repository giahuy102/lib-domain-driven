package com.huyle.ms.domain;

import java.util.Date;
import java.util.UUID;

public abstract class DomainEvent {
    private Date createdAt;
    private UUID eventId;

    public DomainEvent() {
        this.createdAt = new Date();
        this.eventId = UUID.randomUUID();
    }
}
