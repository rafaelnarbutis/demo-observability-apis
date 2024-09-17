package com.shopcar.application.domain.events;

public interface  DomainEvent {
    Types getType();

    Action getAction();

    Object getSource();


    enum Types {
        Transport,
        Address
    }

    enum Action {
        Create,
        Delete,
        Update
    }
}
