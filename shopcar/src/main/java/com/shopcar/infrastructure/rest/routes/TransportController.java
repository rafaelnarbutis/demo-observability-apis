package com.shopcar.infrastructure.rest.routes;

import com.shopcar.application.services.command.enterprise.CreateTransportService;
import com.shopcar.application.services.query.FindTransportsService;
import com.shopcar.infrastructure.rest.dtos.TransportDTOS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransportController {

    private final CreateTransportService createTransportService;
    private final FindTransportsService findTransportsService;

    public TransportController(CreateTransportService createTransportService, FindTransportsService findTransportsService) {
        this.createTransportService = createTransportService;
        this.findTransportsService = findTransportsService;
    }

    @PostMapping("/enterprises/{enterprisesID}/transports")
    @ResponseStatus(HttpStatus.CREATED)
    void executeTransport(@PathVariable("enterprisesID") String enterprisesID,
                          @RequestBody TransportDTOS.TransportPostRequest transportPostRequest
    ) {
        createTransportService.execute(new CreateTransportService.Input(enterprisesID,
                transportPostRequest.type(),
                transportPostRequest.price(),
                transportPostRequest.description())
        );
    }

    @GetMapping("/enterprises/{enterprisesID}/transports")
    ResponseEntity<?> queryTransports(@PathVariable("enterprisesID") String enterprisesID) {
        return ResponseEntity.ok(findTransportsService.execute(new FindTransportsService.Input(enterprisesID)));
    }
}
