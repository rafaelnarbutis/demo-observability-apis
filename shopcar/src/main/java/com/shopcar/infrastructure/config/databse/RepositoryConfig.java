package com.shopcar.infrastructure.config.databse;

import com.shopcar.infrastructure.database.repository.AddressEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.EnterpriseEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.TransportEntityJpaRepository;
import com.shopcar.infrastructure.database.repository.impl.EnterpriseRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    private final EnterpriseEntityJpaRepository enterpriseEntityJpaRepository;
    private final TransportEntityJpaRepository transportEntityJpaRepository;
    private final AddressEntityJpaRepository addressEntityJpaRepository;

    public RepositoryConfig(EnterpriseEntityJpaRepository enterpriseEntityJpaRepository, TransportEntityJpaRepository transportEntityJpaRepository, AddressEntityJpaRepository addressEntityJpaRepository) {
        this.enterpriseEntityJpaRepository = enterpriseEntityJpaRepository;
        this.transportEntityJpaRepository = transportEntityJpaRepository;
        this.addressEntityJpaRepository = addressEntityJpaRepository;
    }

    @Bean
    EnterpriseRepositoryImpl enterpriseRepository() {
        return new EnterpriseRepositoryImpl(enterpriseEntityJpaRepository, transportEntityJpaRepository, addressEntityJpaRepository);
    }
}
