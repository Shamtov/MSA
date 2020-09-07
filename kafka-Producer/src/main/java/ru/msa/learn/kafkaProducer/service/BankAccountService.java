package ru.msa.learn.kafkaProducer.service;

import ru.msa.learn.kafkaProducer.models.BankAccount;

public interface BankAccountService {
    boolean filterAccount(BankAccount bankAccount);
    BankAccount assignRandomAccountType(BankAccount bankAccount);
}
