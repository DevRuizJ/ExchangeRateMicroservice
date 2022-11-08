package com.MiBanco.exchangerateservice.Controller;

import com.MiBanco.exchangerateservice.Model.ExchangeHouse;
import com.MiBanco.exchangerateservice.Model.ExchangeRate;
import com.MiBanco.exchangerateservice.Service.IExchangeRateServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/exchange-rate")
public class ExchangeRateController {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);

    @Autowired
    private IExchangeRateServ serv;

    @GetMapping
    public ResponseEntity<Flux<ExchangeRate>>getExchangeRateList(){

        logger.info("getExchangeRateList - INICIO");

        Flux<ExchangeRate> result = null;

        try{
            result = serv.getAll();
        }catch (Exception ex){
            return new ResponseEntity<Flux<ExchangeRate>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            logger.info("getExchangeRateList - FIN");
        }

        return new ResponseEntity<Flux<ExchangeRate>>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ExchangeRate>> getExchangeRateById(@PathVariable("id") String id){

        logger.info("getExchangeRateById - INICIO");

        Mono<ExchangeRate> result = null;

        try {
            result = serv.getById(id);
        }
        catch (Exception ex){
            logger.info("getExchangeRateById :" + ex.getMessage().toString());
            return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            logger.info("getExchangeRateById - FIN");
        }

        return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Mono<ExchangeRate>> createExchangeRate(@RequestBody ExchangeRate exchangeRate){

        logger.info("createExchangeRate - INICIO");

        Mono<ExchangeRate> result = null;

        try
        {
            result = serv.register(exchangeRate);
        }
        catch (Exception ex){
            logger.info("createExchangeRate - " + ex.getMessage());
            return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            logger.info("createExchangeRate - FIN");
        }

        return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<ExchangeRate>> updateExchangeRate(@RequestBody ExchangeRate exchangeRate, @PathVariable("id") String id){

        logger.info("updateExchangeRate - INICIO");

        Mono<ExchangeRate> result = null;

        try
        {
            result = serv.modify(exchangeRate, id);
        }
        catch (Exception ex){
            return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            logger.info("updateExchangeRate - FIN");
        }

        return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mono<ExchangeRate>> deleteExchangeRate(@PathVariable("id") String id){

        logger.info("deleteExchangeRate - INICIO");

        Mono<ExchangeRate> result = null;

        try
        {
            result = serv.delete(id);
        }
        catch (Exception ex){
            return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            logger.info("deleteExchangeRate - FIN");
        }

        return new ResponseEntity<Mono<ExchangeRate>>(result, HttpStatus.ACCEPTED);
    }

    @GetMapping("/convert")
    public ResponseEntity<Mono<ExchangeHouse>> convertMoney(@RequestBody Float amount, @RequestBody String origin, @RequestBody String destiny){

        logger.info("convertMoney - INICIO");

        Mono<ExchangeHouse> result = null;

        try{
            result = serv.convertMoney(amount, origin, destiny);
        }
        catch (Exception ex){
            logger.info("convertMoney ERROR: " + ex.getMessage().toString() );
            return new ResponseEntity<Mono<ExchangeHouse>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            logger.info("convertMoney - FIN");
        }

        return new ResponseEntity<Mono<ExchangeHouse>>(result, HttpStatus.ACCEPTED);
    }
}
