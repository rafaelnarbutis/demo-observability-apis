package com.shopcar.infrastructure.database.repository.mapper;

import com.shopcar.application.domain.entities.Address;
import com.shopcar.application.domain.entities.vo.AddressId;
import com.shopcar.infrastructure.database.entity.AddressEntity;

import java.util.UUID;

public interface AddressMapper {

    static Address toDomain(AddressEntity address) {
        return Address.create(
                AddressId.create(address.getId().toString()),
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getNumber()
                );
    }

    static AddressEntity toEntity(Address address) {
        final AddressEntity addressEntity = new AddressEntity();

        if(address.getId() != null) addressEntity.setId(UUID.fromString(address.getId().getValue()));

        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setNumber(address.getNumber());

        return addressEntity;
    }
}
