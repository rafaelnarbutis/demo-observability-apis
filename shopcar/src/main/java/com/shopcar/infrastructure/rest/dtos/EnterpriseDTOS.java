package com.shopcar.infrastructure.rest.dtos;

public interface EnterpriseDTOS {

    class EnterprisePostRequest {
        private String cnpj;

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }
    }
}
