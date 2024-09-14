package com.shopcar.infrastructure.rest.routes;

import com.shopcar.application.services.command.enterprise.CreateAddressService;
import com.shopcar.application.services.query.FindAddressService;
import com.shopcar.infrastructure.rest.dtos.AddressDTOS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    private final CreateAddressService createAddressService;
    private final FindAddressService findAddressService;

    public AddressController(CreateAddressService createAddressService, FindAddressService findAddressService) {
        this.createAddressService = createAddressService;
        this.findAddressService = findAddressService;
    }

    @PostMapping("/enterprises/{enterprisesID}/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    void executeAddresses(@PathVariable("enterprisesID") String enterprisesID,
                          @RequestBody AddressDTOS.AddressPostRequest addressPostRequest
    ) {
        createAddressService.execute(new CreateAddressService.Input(enterprisesID,
                addressPostRequest.country(),
                addressPostRequest.city(),
                addressPostRequest.street(),
                addressPostRequest.number())
        );
    }

    @GetMapping("/enterprises/{enterprisesID}/addresses")
    ResponseEntity<?> queryAddress(@PathVariable("enterprisesID") String enterprisesID) {
        return ResponseEntity.ok(findAddressService.execute(new FindAddressService.Input(enterprisesID)));
    }
}
