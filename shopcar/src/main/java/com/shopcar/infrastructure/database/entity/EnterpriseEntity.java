package com.shopcar.infrastructure.database.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class EnterpriseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cnpj;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "enterpriseEntity")
    private List<TransportEntity> transportEntities;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "enterpriseEntity")
    private List<AddressEntity> addressEntities;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<TransportEntity> getTransportEntities() {
        return transportEntities;
    }

    public void setTransportEntities(List<TransportEntity> transportEntities) {
        this.transportEntities = transportEntities;
    }

    public List<AddressEntity> getAddressEntities() {
        return addressEntities;
    }

    public void setAddressEntities(List<AddressEntity> addressEntities) {
        this.addressEntities = addressEntities;
    }
}
