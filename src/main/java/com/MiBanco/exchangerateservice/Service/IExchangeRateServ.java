package com.MiBanco.exchangerateservice.Service;

import com.MiBanco.exchangerateservice.Model.ExchangeHouse;
import com.MiBanco.exchangerateservice.Model.ExchangeRate;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;

public interface IExchangeRateServ extends ICRUD<ExchangeRate, String>{

    public Mono<ExchangeHouse> convertMoney (Float amount, String origin, String destiny);
}
