package ru.msa.learn.kafkaProducer.service;

import org.springframework.stereotype.Service;
import ru.msa.learn.kafkaProducer.models.AccountType;
import ru.msa.learn.kafkaProducer.models.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Override
    public void processChain(BankAccount bankAccount) {
        if (filterAccount(bankAccount)){
            assignAccountType(bankAccount);
            sendMessage(bankAccount);
        }
    }

    @Override
    public boolean filterAccount(BankAccount bankAccount) {
        return bankAccount.getLastName().startsWith("А");
    }

    @Override
    public BankAccount assignAccountType(BankAccount bankAccount) {
        bankAccount.setAccountType(AccountType.ACTIVE);
        return bankAccount;
    }

    @Override
    public void sendMessage(BankAccount bankAccount) {
        System.out.println("to Do");
    }
}
