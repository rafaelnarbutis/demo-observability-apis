package com.shopcar.infrastructure.database.repository;

import com.shopcar.infrastructure.database.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressEntityJpaRepository extends CrudRepository<AddressEntity, UUID> {
}
