package com.shopcar.application.services.query;

import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.application.exceptions.NotFoundException;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.application.services.IODomainService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindTransportsService implements IODomainService<FindTransportsService.Input, List<FindTransportsService.Output>> {

    private final EnterpriseRepository enterpriseRepository;

    public FindTransportsService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = Objects.requireNonNull(enterpriseRepository);
    }

    @Override
    public List<Output> execute(Input input) {
        return enterpriseRepository.getByID(EnterpriseId.create(input.enterpriseID)).map(
                etr -> etr.getTransports().parallelStream()
                        .map(trp -> new Output(
                                trp.getId().getValue(),
                                trp.getType().name(),
                                trp.getPrice(),
                                trp.getDescription())
                        ).collect(Collectors.toList())
        ).orElseThrow(() -> new NotFoundException("enterprise not found"));
    }

    public record Input(String enterpriseID){};
    public record Output(String id, String type, Double price, String description){};
}
