package com.shopcar.application.services.command.enterprise;

import com.shopcar.application.domain.entities.Address;
import com.shopcar.application.domain.entities.Enterprise;
import com.shopcar.application.domain.entities.vo.EnterpriseId;
import com.shopcar.application.exceptions.NotFoundException;
import com.shopcar.application.repositories.EnterpriseRepository;
import com.shopcar.application.services.IDomainService;

import java.util.Objects;

public class CreateAddressService implements IDomainService<CreateAddressService.Input> {

    private final EnterpriseRepository enterpriseRepository;

    public CreateAddressService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = Objects.requireNonNull(enterpriseRepository);
    }

    @Override
    public void execute(Input input) {
        final Enterprise enterprise = enterpriseRepository.getByID(EnterpriseId.create(input.enterpriseID))
                .orElseThrow(() -> new NotFoundException("enterprise not found"));

        enterprise.addAddress(Address.create(input.country, input.city, input.street, input.number));

        enterpriseRepository.change(enterprise);
    }

    public record Input(String enterpriseID,  String country, String city, String street, Integer number){};
}
