package ru.msa.learn.cassandra.request.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.msa.learn.cassandra.request.models.BankAccount;
import ru.msa.learn.cassandra.request.repository.BankAccountRepository;

import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public Mono<BankAccount> getAccountByUuid(UUID uuid) {
        log.info("Trying to get account with UUID: " + uuid.toString());
        return bankAccountRepository.findByUuid(uuid);
    }
}
