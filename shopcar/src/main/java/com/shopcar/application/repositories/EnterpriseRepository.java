package com.shopcar.application.repositories;

import com.shopcar.application.domain.entities.Enterprise;
import com.shopcar.application.domain.entities.vo.EnterpriseId;

import java.util.Optional;

public interface EnterpriseRepository {
    Optional<Enterprise> getByCNPJ(String cnpj);
    Optional<Enterprise> getByID(EnterpriseId enterpriseId);
    void add(Enterprise enterprise);
    void change(Enterprise enterprise);
    void remove(Enterprise enterprise);
}
