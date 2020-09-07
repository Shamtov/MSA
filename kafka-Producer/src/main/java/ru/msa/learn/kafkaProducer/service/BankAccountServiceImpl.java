package ru.msa.learn.kafkaProducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.msa.learn.kafkaProducer.models.AccountType;
import ru.msa.learn.kafkaProducer.models.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private ProduceKafka produceKafka;

    public void execute(BankAccount bankAccount) {
        if (filterAccount(bankAccount)){
            assignRandomAccountType(bankAccount);
            produceKafka.sendMessage(bankAccount);
        }
    }

    @Override
    public boolean filterAccount(BankAccount bankAccount) {
        return bankAccount.getLastName().startsWith("А")||bankAccount.getFirstName().endsWith("а");
    }

    @Override
    public BankAccount assignRandomAccountType(BankAccount bankAccount) {
        bankAccount.setAccountType(AccountType.getRandomType());
        return bankAccount;
    }

    @Override
    public void invokeKafka(BankAccount bankAccount) {
        System.out.println("to Do");
    }
}
