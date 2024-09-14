package com.shopcar.application.services.command.enterprise;

import com.shopcar.application.domain.entities.Enterprise;
import com.shopcar.application.domain.entities.Transport;
import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.application.domain.entities.vo.TransportType;
import com.shopcar.application.exceptions.NotFoundException;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.application.services.IDomainService;

import java.util.Objects;

public class CreateTransportService implements IDomainService<CreateTransportService.Input> {

    private final EnterpriseRepository enterpriseRepository;

    public CreateTransportService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = Objects.requireNonNull(enterpriseRepository);
    }

    @Override
    public void execute(Input input) {
        final Enterprise enterprise = enterpriseRepository.getByID(EnterpriseId.create(input.enterpriseID))
                .orElseThrow(() -> new NotFoundException("enterprise not found"));

        enterprise.addTransport(Transport.create(TransportType.valueOf(input.transportType.toUpperCase()),
                    input.price,
                    input.description
                )
        );

        enterpriseRepository.change(enterprise);
    }

    public record Input(String enterpriseID, String transportType, Double price, String description){};
}
