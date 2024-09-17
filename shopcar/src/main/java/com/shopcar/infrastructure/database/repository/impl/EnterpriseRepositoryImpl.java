package com.shopcar.infrastructure.database.repository.impl;

import com.shopcar.application.domain.entities.Address;
import com.shopcar.application.domain.entities.Enterprise;
import com.shopcar.application.domain.entities.Transport;
import com.shopcar.application.domain.entities.vo.AddressId;
import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.application.domain.entities.vo.TransportId;
import com.shopcar.application.domain.events.DomainEvent;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.infrastructure.database.repository.AddressEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.EnterpriseEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.TransportEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.mapper.AddressMapper;
import com.shopcar.infrastructure.database.repository.mapper.EnterpriseMapper;
import com.shopcar.infrastructure.database.repository.mapper.TransportMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class EnterpriseRepositoryImpl implements EnterpriseRepository {

    private final EnterpriseEntityJpaRepository enterpriseEntityJpaRepository;
    private final TransportEntityJpaRepository transportEntityJpaRepository;
    private final AddressEntityJpaRepository addressEntityJpaRepository;

    public EnterpriseRepositoryImpl(
            EnterpriseEntityJpaRepository enterpriseEntityJpaRepository,
            TransportEntityJpaRepository transportEntityJpaRepository, AddressEntityJpaRepository addressEntityJpaRepository) {
        this.enterpriseEntityJpaRepository = Objects.requireNonNull(enterpriseEntityJpaRepository);
        this.transportEntityJpaRepository = transportEntityJpaRepository;
        this.addressEntityJpaRepository = addressEntityJpaRepository;
    }

    @Override
    public Optional<Enterprise> getByCNPJ(String cnpj) {
        return enterpriseEntityJpaRepository.findFirstByCnpj(cnpj)
                .map(enterpriseEntity -> EnterpriseMapper.toDomain(enterpriseEntity));
    }

    @Override
    public Optional<Enterprise> getByID(EnterpriseId enterpriseId) {
        return enterpriseEntityJpaRepository.findById(UUID.fromString(enterpriseId.getValue()))
                .map(enterpriseEntity -> EnterpriseMapper.toDomain(enterpriseEntity));
    }

    @Override
    public void add(Enterprise enterprise) {
        enterpriseEntityJpaRepository.save(EnterpriseMapper.toEntity(enterprise));
    }

    @Override
    @Transactional
    public void change(Enterprise enterprise) {
        try {
            Thread.sleep((ThreadLocalRandom.current().nextInt(1, 6 + 1)) * 1000L);
        } catch (Exception e) {}

        enterprise.getEvents()
                .forEach(domainEvent -> {
                    if (domainEvent.getType() == DomainEvent.Types.Transport) {
                        if(domainEvent.getAction() == DomainEvent.Action.Create || domainEvent.getAction() == DomainEvent.Action.Update) {
                            var entity = TransportMapper.toEntity((Transport) domainEvent.getSource());
                            entity.setEnterpriseEntity(EnterpriseMapper.toEntity(enterprise));
                            transportEntityJpaRepository.save(entity);
                        } else if (domainEvent.getAction() == DomainEvent.Action.Delete) {
                            final UUID id = UUID.fromString(((TransportId) domainEvent.getSource()).getValue());
                            transportEntityJpaRepository.deleteById(id);
                        }
                    } else if (domainEvent.getType() == DomainEvent.Types.Address) {
                        if(domainEvent.getAction() == DomainEvent.Action.Create || domainEvent.getAction() == DomainEvent.Action.Update) {
                            var entity = AddressMapper.toEntity((Address) domainEvent.getSource());
                            entity.setEnterpriseEntity(EnterpriseMapper.toEntity(enterprise));
                            addressEntityJpaRepository.save(entity);
                        } else if (domainEvent.getAction() == DomainEvent.Action.Delete) {
                            final UUID id = UUID.fromString(((AddressId) domainEvent.getSource()).getValue());
                            addressEntityJpaRepository.deleteById(id);
                        }
                    }
                });

        enterprise.clearEvents();
    }

    @Override
    public void remove(Enterprise enterprise) {
        enterpriseEntityJpaRepository.deleteById(UUID.fromString(enterprise.getId().getValue()));
    }
}
