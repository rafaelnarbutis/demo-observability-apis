package com.shopcar.infrastructure.database.repository.impl;

import com.shopcar.application.domain.entities.Enterprise;
import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.infrastructure.database.entity.EnterpriseEntity;
import com.shopcar.infrastructure.database.repository.AddressEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.EnterpriseEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.TransportEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.mapper.AddressMapper;
import com.shopcar.infrastructure.database.repository.mapper.EnterpriseMapper;
import com.shopcar.infrastructure.database.repository.mapper.TransportMapper;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class EnterpriseRepositoryImpl implements EnterpriseRepository {

    private final EnterpriseEntityJpaRepository enterpriseEntityJpaRepository;

    public EnterpriseRepositoryImpl(
            EnterpriseEntityJpaRepository enterpriseEntityJpaRepository
    ) {
        this.enterpriseEntityJpaRepository = Objects.requireNonNull(enterpriseEntityJpaRepository);
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
    public void change(Enterprise enterprise) {
        enterpriseEntityJpaRepository.save(EnterpriseMapper.toEntity(enterprise));
    }

    @Override
    public void remove(Enterprise enterprise) {
        enterpriseEntityJpaRepository.deleteById(UUID.fromString(enterprise.getId().getValue()));
    }
}
