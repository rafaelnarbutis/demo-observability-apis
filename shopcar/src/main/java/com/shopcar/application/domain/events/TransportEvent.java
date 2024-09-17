package com.shopcar.application.domain.events;

import com.shopcar.application.domain.entities.Transport;
import com.shopcar.application.domain.entities.vo.TransportId;

import java.time.LocalDateTime;

public interface TransportEvent {

    record Created(String version, LocalDateTime dateTime, Transport transport) implements DomainEvent {
        @Override
        public Types getType() {
            return Types.Transport;
        }

        @Override
        public Action getAction() {
            return Action.Create;
        }

        @Override
        public Object getSource() {
            return transport;
        }
    }

    record Deleted(String version, LocalDateTime dateTime, TransportId transportId) implements DomainEvent {
        @Override
        public Types getType() {
            return Types.Transport;
        }

        @Override
        public Action getAction() {
            return Action.Delete;
        }

        @Override
        public Object getSource() {
            return transportId;
        }
    }

     record Updated(String version, LocalDateTime dateTime, Transport transport) implements DomainEvent {
         @Override
         public Types getType() {
             return Types.Transport;
         }

         @Override
         public Action getAction() {
             return Action.Update;
         }

         @Override
         public Object getSource() {
             return transport;
         }
     }
}
