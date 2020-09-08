package ru.msa.learn.kafkaConsumer.service;

import ru.msa.learn.kafkaConsumer.models.BankAccount;

import java.util.List;

public interface BankAccountService {
    void saveAllAccounts(List<BankAccount> bankAccount);
}
