package com.MiBanco.exchangerateservice.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUD<T, V> {

    Mono<T> register(T obj);
    Mono<T> modify(T obj, V id);
    Flux<T> getAll();
    Mono<T> getById(V id);
    Mono<T> delete(V id);

}
