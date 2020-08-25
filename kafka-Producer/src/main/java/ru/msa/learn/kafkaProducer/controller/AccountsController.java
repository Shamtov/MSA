package ru.msa.learn.kafkaProducer.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.msa.learn.kafkaProducer.models.BankAccount;

@RestController
public class AccountsController {
    RestTemplate restTemplate;

    public BankAccount getAccount() {
        BankAccount bankAccount = restTemplate.getForObject("${bankAccountURL}", BankAccount.class);
        System.out.println(bankAccount);
        return bankAccount;
    }
}
