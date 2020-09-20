package ru.msa.learn.grpcservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.msa.learn.grpcservice.model.BankAccountInfo;
import ru.msa.learn.grpcservice.repository.BankAccountInfoRepository;

@Service
public class BankAccountServiceImpl {
    private Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    @Autowired
    BankAccountInfoRepository bankAccountInfoRepository;

   public Flux<BankAccountInfo> getAccounts() {
       Flux<BankAccountInfo> infoFlux = bankAccountInfoRepository.findAll();
       return infoFlux;
    }
}
