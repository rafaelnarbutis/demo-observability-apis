package com.shopcar.application.domain.events;

import com.shopcar.application.domain.entities.Address;
import com.shopcar.application.domain.entities.vo.AddressId;

import java.time.LocalDateTime;

public interface AddressEvent {

    record Created(String version, LocalDateTime dateTime, Address address) implements DomainEvent {
        @Override
        public Types getType() {
            return Types.Address;
        }

        @Override
        public Action getAction() {
            return Action.Create;
        }

        @Override
        public Object getSource() {
            return address;
        }
    }

    record Deleted(String version, LocalDateTime dateTime, AddressId addressId) implements DomainEvent {
        @Override
        public DomainEvent.Types getType() {
            return DomainEvent.Types.Address;
        }

        @Override
        public DomainEvent.Action getAction() {
            return Action.Delete;
        }

        @Override
        public Object getSource() {
            return addressId;
        }
    }

    record Updated(String version, LocalDateTime dateTime, Address address) implements DomainEvent {
        @Override
        public DomainEvent.Types getType() {
            return DomainEvent.Types.Address;
        }

        @Override
        public DomainEvent.Action getAction() {
            return Action.Update;
        }

        @Override
        public Object getSource() {
            return address;
        }
    }
}
