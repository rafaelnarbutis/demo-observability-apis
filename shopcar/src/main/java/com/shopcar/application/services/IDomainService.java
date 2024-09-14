package com.shopcar.application.services;

public interface IDomainService<INPUT> {
    void execute(INPUT input);
}
