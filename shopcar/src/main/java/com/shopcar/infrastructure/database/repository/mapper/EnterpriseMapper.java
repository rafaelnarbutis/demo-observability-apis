package com.shopcar.infrastructure.database.repository.mapper;

import com.shopcar.application.domain.entities.Address;
import com.shopcar.application.domain.entities.Enterprise;
import com.shopcar.application.domain.entities.vo.AddressId;
import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.infrastructure.database.entity.AddressEntity;
import com.shopcar.infrastructure.database.entity.EnterpriseEntity;

import java.util.UUID;
import java.util.stream.Collectors;

public interface EnterpriseMapper {

    static Enterprise toDomain(EnterpriseEntity enterprise) {
        return Enterprise.create(
                EnterpriseId.create(enterprise.getId().toString()),

                enterprise.getCnpj(),

                enterprise.getAddressEntities() != null ?
                        enterprise.getAddressEntities().stream()
                                .map(adr -> AddressMapper.toDomain(adr))
                                .collect(Collectors.toList()) : null,

                enterprise.getTransportEntities() != null ?
                        enterprise.getTransportEntities().stream()
                                .map(trp -> TransportMapper.toDomain(trp))
                                .collect(Collectors.toList()): null
        );
    }

    static EnterpriseEntity toEntity(Enterprise enterprise) {
        final EnterpriseEntity enterpriseEntity = new EnterpriseEntity();

        if(enterprise.getId() != null) enterpriseEntity.setId(UUID.fromString(enterprise.getId().getValue()));

        enterpriseEntity.setCnpj(enterprise.getCnpj());

        enterpriseEntity.setAddressEntities(enterprise.getAddresses() != null
                ? enterprise.getAddresses().stream().map(adr -> AddressMapper.toEntity(adr)).collect(Collectors.toList())
                : null
        );

        enterpriseEntity.setTransportEntities(enterprise.getTransports() != null
                ? enterprise.getTransports().stream().map(trp -> TransportMapper.toEntity(trp)).collect(Collectors.toList())
                : null);

        return enterpriseEntity;
    }
}
