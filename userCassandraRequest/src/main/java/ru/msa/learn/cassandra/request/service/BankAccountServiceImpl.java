package ru.msa.learn.cassandra.request.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.msa.learn.cassandra.request.models.AccountType;
import ru.msa.learn.cassandra.request.models.Address;
import ru.msa.learn.cassandra.request.models.BankAccount;
import ru.msa.learn.cassandra.request.models.BankAccountInfo;
import ru.msa.learn.cassandra.request.repository.BankAccountInfoRepository;

import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    @Autowired
    BankAccountInfoRepository bankAccountInfoRepository;

    @Override
    public Mono<BankAccountInfo> getAccountByUuid(UUID uuid) {
        log.info("Trying to get account with UUID: " + uuid.toString());
        return bankAccountInfoRepository.findByUuid(uuid);
    }

/*    public Mono<BankAccountInfo> saveAccount(){

        Address address = new Address("street2","city2","state2");
        BankAccount account = new BankAccount("Name2","lastName2","Patronymic2",1234567, AccountType.getRandomType());
        BankAccountInfo info = new BankAccountInfo(UUID.randomUUID(), account, address);

        return bankAccountInfoRepository.insert(info);
    }*/
}
