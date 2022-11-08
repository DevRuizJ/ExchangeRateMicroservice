package com.MiBanco.exchangerateservice.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "exchange_rate")
public class ExchangeRate {

    @Id
    private String id;

    private LocalDate date;

    private Float exchangeRate;

    private String observation;

    private String message;

}
