package ru.msa.learn.grpc.client.service;

import io.netty.channel.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.msa.learn.AccountTypeMessage;
import ru.msa.learn.BankAccountMessage;
import ru.msa.learn.RequestGrpc;
import ru.msa.learn.RequestMessage;
import ru.msa.learn.grpc.client.controller.BankAccountInfoController;
import ru.msa.learn.grpc.client.model.AccountType;
import ru.msa.learn.grpc.client.model.BankAccountInfo;

import java.util.ArrayList;
import java.util.List;


@Service
public class BankAccountInfoServiceImpl extends RequestGrpc.RequestImplBase {
    private Logger log = LoggerFactory.getLogger(BankAccountInfoServiceImpl.class);
    @Autowired
    RequestGrpc.RequestBlockingStub blockingStub;
    @Autowired
    private List<BankAccountInfo> list;

    public Flux<BankAccountInfo> getAccountByType(AccountType type) {
        try {
            blockingStub.getBankAccountInfo(RequestMessage.
                    newBuilder().
                    setType(AccountTypeMessage.valueOf(type.name()))
                    .build()).
                    forEachRemaining(bankAccountInfoMessage -> {
                        log.info("Received message :" + list);
                        list.add(new BankAccountInfo(bankAccountInfoMessage.getUuid(),
                                bankAccountInfoMessage.getAccount(),
                                bankAccountInfoMessage.getAddress()));
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
}
