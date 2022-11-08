package com.MiBanco.exchangerateservice.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "exchange_house")
public class ExchangeHouse {

    @Id
    private Integer id;

    private Float amount;

    private Float totalChange;

    private String origin;

    private String destiny;

    private Float exchangeRate;
}
