package com.shopcar.application.services.query;

import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.application.exceptions.NotFoundException;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.application.services.IODomainService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindAddressService implements IODomainService<FindAddressService.Input, List<FindAddressService.Output>> {

    private final EnterpriseRepository enterpriseRepository;

    public FindAddressService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = Objects.requireNonNull(enterpriseRepository);
    }

    @Override
    public List<Output> execute(Input input) {
        return enterpriseRepository.getByID(EnterpriseId.create(input.enterpriseID)).map(
                etr -> etr.getAddresses().parallelStream()
                        .map(adr -> new Output(
                                adr.getId().getValue(),
                                adr.getCountry(),
                                adr.getCity(),
                                adr.getStreet(),
                                adr.getNumber()
                                )
                        ).collect(Collectors.toList())
        ).orElseThrow(() -> new NotFoundException("enterprise not found"));
    }

    public record Input(String enterpriseID){};
    public record Output(String id, String country, String city, String street, Integer number){};
}
