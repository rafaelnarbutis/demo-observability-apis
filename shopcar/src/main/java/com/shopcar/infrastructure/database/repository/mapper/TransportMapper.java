package com.shopcar.infrastructure.database.repository.mapper;

import com.shopcar.application.domain.entities.Transport;
import com.shopcar.application.domain.entities.vo.TransportId;
import com.shopcar.infrastructure.database.entity.TransportEntity;

import java.util.UUID;

public interface TransportMapper {
    static Transport toDomain(TransportEntity transport) {
        return Transport.create(
                TransportId.create(transport.getId().toString()),
                transport.getType(),
                transport.getPrice(),
                transport.getDescription()
        );
    }

    static TransportEntity toEntity(Transport transport) {
        final TransportEntity transportEntity = new TransportEntity();

        if(transport.getId() != null) transportEntity.setId(UUID.fromString(transport.getId().getValue()));

        transportEntity.setType(transport.getType());
        transportEntity.setPrice(transport.getPrice());
        transportEntity.setDescription(transport.getDescription());

        return transportEntity;
    }
}
