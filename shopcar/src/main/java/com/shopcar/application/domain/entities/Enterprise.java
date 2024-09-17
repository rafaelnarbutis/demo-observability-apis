package com.shopcar.application.domain.entities;


import com.shopcar.application.domain.entities.vo.AddressId;
import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.application.domain.entities.vo.TransportId;
import com.shopcar.application.domain.events.AddressEvent;
import com.shopcar.application.domain.events.TransportEvent;
import com.shopcar.application.exceptions.ValidationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enterprise extends Aggregate {
    private EnterpriseId id;

    private String cnpj;

    private List<Address> addresses;

    private List<Transport> transports;

    private Enterprise(String cnpj, List<Address> addresses, List<Transport> transports){
        this.cnpj = cnpj;
        this.addresses = addresses;
        this.transports = transports;
    }

    private Enterprise(EnterpriseId id, String cnpj, List<Address> addresses, List<Transport> transports) {
        this.id = id;
        this.cnpj = cnpj;
        this.addresses = addresses;
        this.transports = transports;
    }


    public static final Enterprise create(String cnpj) {
        if(cnpj == null || cnpj.isBlank()) throw new ValidationException("invalid cnpj");

        return new Enterprise(cnpj, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    public static final Enterprise create(EnterpriseId id, String cnpj, List<Address> addresses, List<Transport> transports) {
        if(cnpj == null || cnpj.isBlank()) throw new ValidationException("invalid cnpj");

        return new Enterprise(id, cnpj, addresses, transports);
    }

    public void addAddress(Address address) {
        if(this.addresses == null) this.addresses = new ArrayList<>();

        this.addresses.stream().filter(adr -> adr.equals(address)).findFirst().ifPresentOrElse(
                adr -> { throw new ValidationException("address already exist"); },
                () -> this.addresses.add(address)
        );

        addEvent(new AddressEvent.Created(EVENT_VERSION, LocalDateTime.now(), address));
    }

    public void removeAddress(AddressId addressId) {
        if (this.addresses == null) throw new ValidationException("address not found");

        this.addresses.stream().filter(adr -> adr.getId().equals(addressId)).findFirst().ifPresentOrElse(
                adr -> this.addresses.remove(adr),
                () -> { throw  new ValidationException("address not found"); }
        );

        addEvent(new AddressEvent.Deleted(EVENT_VERSION, LocalDateTime.now(), addressId));
    }

    public void addTransport(Transport transport) {
        if(this.transports == null) this.transports = new ArrayList<>();

        this.transports.stream().filter(trp -> trp.equals(transport)).findFirst().ifPresentOrElse(
                trp -> { throw  new ValidationException("transport already exist"); },
                () -> this.transports.add(transport)
        );

        addEvent(new TransportEvent.Created(EVENT_VERSION, LocalDateTime.now(), transport));
    }

    public void removeTransport(TransportId transportId) {
        if (this.transports == null) throw new ValidationException("transport not found");

        this.transports.stream().filter(trp -> trp.getId().equals(transportId)).findFirst().ifPresentOrElse(
                trp -> this.transports.remove(trp),
                () -> { throw  new ValidationException("transport not found"); }
        );

        addEvent(new TransportEvent.Deleted(EVENT_VERSION, LocalDateTime.now(), transportId));
    }

    public final EnterpriseId getId() {
        return id;
    }

    public final String getCnpj() {
        return cnpj;
    }

    public final List<Address> getAddresses() {
        return Collections.unmodifiableList(this.addresses);
    }

    public final List<Transport> getTransports() {
        return Collections.unmodifiableList(this.transports);
    }
}
