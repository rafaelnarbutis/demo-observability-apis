package com.shopcar.application.domain.entities;

import com.shopcar.application.domain.events.DomainEvent;

import java.util.*;

public abstract class Aggregate {
    private final List<DomainEvent> events = new ArrayList<>();

    static final String EVENT_VERSION = "V1";


    public void addEvent(DomainEvent eventSource) {
        this.events.add(eventSource);
    }

    public List<DomainEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void clearEvents() {
        this.events.clear();
    }
}
