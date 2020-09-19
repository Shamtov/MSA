package ru.msa.learn.cassandra.request.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.msa.learn.cassandra.request.models.BankAccountInfo;
import ru.msa.learn.cassandra.request.service.BankAccountServiceImpl;

import java.util.UUID;

@RestController
public class BankAccountController {
    Logger log = LoggerFactory.getLogger(BankAccountController.class);
    @Autowired
    BankAccountServiceImpl service;

    @GetMapping("account/{uuid}")
    public Mono<BankAccountInfo> getAccount(@PathVariable UUID uuid) {
            return service.getAccountByUuid(uuid);
    }

   /* @GetMapping("account/save")
    public Mono<BankAccountInfo> saveAccount() {
        return service.saveAccount();
    }*/
}
