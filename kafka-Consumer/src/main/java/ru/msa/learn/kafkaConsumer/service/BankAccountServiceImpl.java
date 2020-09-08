package ru.msa.learn.kafkaConsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.msa.learn.kafkaConsumer.models.BankAccount;
import ru.msa.learn.kafkaConsumer.repository.BankAccountRepository;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public void saveAllAccounts(List<BankAccount> bankAccount) {
        log.info("Save account in Cassandra" + bankAccount.toString());
        bankAccountRepository.saveAll(bankAccount).subscribe();
    }
}
