package ru.msa.learn.grpc.client.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.msa.learn.grpc.client.model.AccountType;
import ru.msa.learn.grpc.client.model.BankAccountInfo;
import ru.msa.learn.grpc.client.service.BankAccountInfoServiceImpl;

import java.util.Optional;

@RestController
public class BankAccountInfoController {
    private Logger log = LoggerFactory.getLogger(BankAccountInfoController.class);
    @Autowired
    BankAccountInfoServiceImpl service;

    @GetMapping("account/{type}")
    public ResponseEntity<Flux<BankAccountInfo>> getAccount(@PathVariable String type) {
        log.info("Input: " + type);
        if (!BankAccountInfoServiceImpl.contains(type)) {
            return ResponseEntity.notFound().build();
        }
        Flux<BankAccountInfo> infoFlux = service.getAccountByType(AccountType.valueOf(type));

        return ResponseEntity.ok(infoFlux);
    }
}
