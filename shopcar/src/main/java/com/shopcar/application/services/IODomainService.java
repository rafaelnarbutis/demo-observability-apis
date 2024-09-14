package com.shopcar.application.services;

public interface IODomainService<INPUT, OUTPUT> {
    OUTPUT execute(INPUT input);
}
