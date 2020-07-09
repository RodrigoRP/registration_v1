package com.rodrigoramos.registration.service;

public interface GenericService<E, M> {

    E save(E entity);

    E findById(M id);
}
