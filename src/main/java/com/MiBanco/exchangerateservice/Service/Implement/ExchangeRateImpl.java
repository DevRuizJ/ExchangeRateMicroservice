package com.MiBanco.exchangerateservice.Service.Implement;

import com.MiBanco.exchangerateservice.Model.ExchangeHouse;
import com.MiBanco.exchangerateservice.Model.ExchangeRate;
import com.MiBanco.exchangerateservice.Repository.IExchangeRateRepo;
import com.MiBanco.exchangerateservice.Service.IExchangeRateServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExchangeRateImpl implements IExchangeRateServ {

    @Autowired
    private IExchangeRateRepo repo;

    @Override
    public Mono<ExchangeRate> register(ExchangeRate obj) {
        return repo.save(obj);
    }

    @Override
    public Mono<ExchangeRate> modify(ExchangeRate obj, String id) {
        return repo.findById(id)
                .doOnNext(e -> e.setId(id))
                .flatMap(updateExhangeRate -> repo.save(obj).then(Mono.just(obj)));
    }

    @Override
    public Flux<ExchangeRate> getAll() {
        return repo.findAll().switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<ExchangeRate> getById(String id) {
        return repo.findById(id);
    }

    @Override
    public Mono<ExchangeRate> delete(String id) {
        return getById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .flatMap(e -> repo.delete(e).then(Mono.just(e)));
    }

    @Override
    public Mono<ExchangeHouse> convertMoney(Float amount, String origin, String destiny) {

       //Float exchangeRate = getById("20221108").block(Duration.ofMillis(10000)).getExchangeRate();

       Float response = amount * 3.50f;

       Mono<ExchangeHouse> exchangeHouse = null;

       exchangeHouse
               .doOnNext(e -> e.setAmount(amount))
               .doOnNext(e -> e.setTotalChange(response))
               .doOnNext(e -> e.setOrigin(origin))
               .doOnNext(e -> e.setDestiny(destiny))
               .doOnNext(e -> e.setExchangeRate(3.50f));

        return exchangeHouse;

    }
}
