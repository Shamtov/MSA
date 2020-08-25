package ru.msa.learn.kafkaProducer.service;

import ru.msa.learn.kafkaProducer.models.BankAccount;

public interface BankAccountService {
    void processChain (BankAccount bankAccount);
    boolean filterAccount(BankAccount bankAccount);
    BankAccount assignAccountType(BankAccount bankAccount);
    void sendMessage(BankAccount bankAccount);
}
