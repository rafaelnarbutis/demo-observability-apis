package com.shopcar.application.services.query;

import com.shopcar.application.exceptions.NotFoundException;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.application.services.IODomainService;

import java.util.Objects;

public class FindEnterpriseService implements IODomainService<FindEnterpriseService.Input, FindEnterpriseService.Output> {

    private final EnterpriseRepository enterpriseRepository;

    public FindEnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = Objects.requireNonNull(enterpriseRepository);
    }

    @Override
    public Output execute(Input input) {
        return enterpriseRepository.getByCNPJ(input.cnpj).map(
                etr -> new Output(etr.getId().getValue(), etr.getCnpj())
        ).orElseThrow(() -> new NotFoundException("enterprise not found"));
    }

    public record Input(String cnpj){};
    public record Output(String id, String cnpj){};
}
