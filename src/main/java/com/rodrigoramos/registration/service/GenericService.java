package com.rodrigoramos.registration.service;

import java.util.List;

public interface GenericService<E, M> {

    E save(E entity);

    E findById(M id);

    List<E> findAll();

    void deleteById(M id);

  //  E update(E entity, M id);
}
