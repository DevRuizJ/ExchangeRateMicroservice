package com.MiBanco.exchangerateservice.Repository;

import com.MiBanco.exchangerateservice.Model.ExchangeRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface IExchangeRateRepo extends ReactiveMongoRepository<ExchangeRate, String> {

}
