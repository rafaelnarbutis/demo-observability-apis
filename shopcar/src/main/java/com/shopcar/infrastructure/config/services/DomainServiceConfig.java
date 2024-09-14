package com.shopcar.infrastructure.config.services;

import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.application.services.command.enterprise.CreateAddressService;
import com.shopcar.application.services.command.enterprise.CreateEnterpriseService;
import com.shopcar.application.services.command.enterprise.CreateTransportService;
import com.shopcar.application.services.query.FindAddressService;
import com.shopcar.application.services.query.FindEnterpriseService;
import com.shopcar.application.services.query.FindTransportsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfig {

    private final EnterpriseRepository enterpriseRepository;

    public DomainServiceConfig(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Bean
    public CreateEnterpriseService createEnterpriseService() {return new CreateEnterpriseService(enterpriseRepository);}

    @Bean
    public CreateTransportService createTransportService() {
        return new CreateTransportService(enterpriseRepository);
    }

    @Bean
    public CreateAddressService createAddressService() {
        return new CreateAddressService(enterpriseRepository);
    }

    @Bean
    public FindEnterpriseService findEnterpriseService() {
        return new FindEnterpriseService(enterpriseRepository);
    }

    @Bean
    public FindTransportsService findTransportsService() { return new FindTransportsService(enterpriseRepository);}

    @Bean
    public FindAddressService findAddressService() {return new FindAddressService(enterpriseRepository); }
}
