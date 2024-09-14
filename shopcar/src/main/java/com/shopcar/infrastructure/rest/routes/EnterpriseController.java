package com.shopcar.infrastructure.rest.routes;

import com.shopcar.application.services.command.enterprise.CreateEnterpriseService;
import com.shopcar.application.services.query.FindEnterpriseService;
import com.shopcar.infrastructure.rest.dtos.EnterpriseDTOS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnterpriseController {

    private final CreateEnterpriseService createEnterpriseService;
    private final FindEnterpriseService findEnterpriseService;

    public EnterpriseController(CreateEnterpriseService createEnterpriseService, FindEnterpriseService findEnterpriseService) {
        this.createEnterpriseService = createEnterpriseService;
        this.findEnterpriseService = findEnterpriseService;
    }

    @PostMapping("/enterprises")
    @ResponseStatus(HttpStatus.CREATED)
    void executeEnterprise(@RequestBody EnterpriseDTOS.EnterprisePostRequest enterprisePostRequest) {
        createEnterpriseService.execute(new CreateEnterpriseService.Input(enterprisePostRequest.getCnpj()));
    }

    @GetMapping("/enterprises")
    ResponseEntity<?> queryEnterprise(@RequestParam("cnpj") String cnpj) {
        return ResponseEntity.ok(findEnterpriseService.execute(new FindEnterpriseService.Input(cnpj)));
    }
}
