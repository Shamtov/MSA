package ru.msa.learn.grpc.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.msa.learn.*;
import ru.msa.learn.grpc.client.model.AccountType;
import ru.msa.learn.grpc.client.model.Address;
import ru.msa.learn.grpc.client.model.BankAccount;
import ru.msa.learn.grpc.client.model.BankAccountInfo;

import java.util.ArrayList;
import java.util.List;


@Service
public class BankAccountInfoServiceImpl {
    private final Logger log = LoggerFactory.getLogger(BankAccountInfoServiceImpl.class);
    @Autowired
    RequestGrpc.RequestBlockingStub blockingStub;

    public Flux<BankAccountInfo> getAccountByType(AccountType type) {
        List<BankAccountInfo> list = getList();
        try {
            blockingStub.getBankAccountInfo(RequestMessage.
                    newBuilder().
                    setType(AccountTypeMessage.valueOf(type.name()))
                    .build()).
                    forEachRemaining(bankAccountInfoMessage -> {
                        //log.info("Received message :" + bankAccountInfoMessage);
                        list.add(parseObject(bankAccountInfoMessage));
                    });
        } catch (Exception e) {
            log.error(e.getCause() + e.getMessage() + e.getStackTrace());
        }
        return Flux.fromIterable(list);
    }

    public static boolean contains(String test) {
        for (AccountType a : AccountType.values()) {
            if (a.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    private List<BankAccountInfo> getList(){
        return new ArrayList<>(100);
    }

    private BankAccountInfo parseObject(BankAccountInfoMessage message) {
        BankAccountInfo bankAccountInfo = new BankAccountInfo();
        bankAccountInfo.setUuid(message.getUuid().getUuid());
        BankAccount bankAccount = new BankAccount(message.getAccountOrBuilder().getFirstName(),
                message.getAccountOrBuilder().getLastName(),
                message.getAccountOrBuilder().getPatronymic(),
                message.getAccountOrBuilder().getAccountNumber(),
                AccountType.valueOf(message.getAccountOrBuilder().getType().name()));
        Address address = new Address(message.getAddressOrBuilder().getStreet(),
                message.getAddressOrBuilder().getCity(),
                message.getAddressOrBuilder().getState());
        bankAccountInfo.setBankAccount(bankAccount);
        bankAccountInfo.setAddress(address);
        return bankAccountInfo;
    }
}
