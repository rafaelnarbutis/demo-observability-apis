package com.shopcar.application.services.command.enterprise;

import com.shopcar.application.domain.entities.Enterprise;
import com.shopcar.application.exceptions.ValidationException;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.application.services.IDomainService;

import java.util.Objects;

public class CreateEnterpriseService implements IDomainService<CreateEnterpriseService.Input> {

    private final EnterpriseRepository enterpriseRepository;

    public CreateEnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = Objects.requireNonNull(enterpriseRepository);
    }

    @Override
    public void execute(Input input) {
        enterpriseRepository.getByCNPJ(input.cnpj).ifPresent(etr -> {
                throw new ValidationException("Enterprise already exist");
            }
        );

        final Enterprise enterprise = Enterprise.create(input.cnpj);

        enterpriseRepository.add(enterprise);
    }

    public record Input(String cnpj){};
}
