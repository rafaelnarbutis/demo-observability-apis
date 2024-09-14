package com.shopcar.infrastructure.database.repository;

import com.shopcar.infrastructure.database.entity.TransportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransportEntityJpaRepository extends CrudRepository<TransportEntity, UUID> {
}
