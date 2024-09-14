package com.shopcar.infrastructure.database.repository;

import com.shopcar.infrastructure.database.entity.EnterpriseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnterpriseEntityJpaRepository extends CrudRepository<EnterpriseEntity, UUID> {

    Optional<EnterpriseEntity> findFirstByCnpj(String cnpj);
}
