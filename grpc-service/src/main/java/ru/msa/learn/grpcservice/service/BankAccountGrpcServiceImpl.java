package ru.msa.learn.grpcservice.service;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import ru.msa.learn.*;
import ru.msa.learn.grpcservice.model.BankAccountInfo;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService()
public class BankAccountGrpcServiceImpl extends RequestGrpc.RequestImplBase {
    private final Logger log = LoggerFactory.getLogger(BankAccountGrpcServiceImpl.class);

    @Autowired
    private BankAccountServiceImpl service;

    @Override
    public void getBankAccountInfo(RequestMessage request, StreamObserver<BankAccountInfoMessage> responseObserver) {
        log.info("Received message: " + request.getType().name());
        AccountTypeMessage accountTypeMessage = request.getType();
        Flux<BankAccountInfo> accounts = service.getAccounts();
        List<BankAccountInfo> filteredList = getBankAccountInfos(accountTypeMessage, accounts);
        filteredList.forEach(bankAccountInfo -> {
            BankAccountInfoMessage message = createBankAccountInfoMessage(createUUIDMessage(bankAccountInfo), createAccountTypeMessage(bankAccountInfo, accountTypeMessage), createAddressMessage(bankAccountInfo));
            responseObserver.onNext(message);
            log.info("Server responded " + message);
        });
        responseObserver.onCompleted();
    }

    private AddressMessage createAddressMessage(BankAccountInfo info) {
        return AddressMessage
                .newBuilder()
                .setCity(info.getAddress().getCity())
                .setState(info.getAddress().getState())
                .setStreet(info.getAddress().getStreet())
                .build();
    }

    private BankAccountMessage createAccountTypeMessage(BankAccountInfo info, AccountTypeMessage accountTypeMessage) {
        return BankAccountMessage
                .newBuilder()
                .setFirstName(info.getBankAccount().getFirstName())
                .setLastName(info.getBankAccount().getLastName())
                .setPatronymic(info.getBankAccount().getPatronymic())
                .setAccountNumber(info.getBankAccount().getAccountNumber())
                .setType(accountTypeMessage)
                .build();
    }

    private UUID createUUIDMessage(BankAccountInfo info) {
        return UUID.newBuilder().
                setUuid(info.getUuid().toString())
                .build();
    }

    private BankAccountInfoMessage createBankAccountInfoMessage(UUID uuid, BankAccountMessage accountMessage, AddressMessage addressMessage) {
        BankAccountInfoMessage bankAccountInfoMessage = BankAccountInfoMessage
                .newBuilder()
                .setUuid(uuid)
                .setAccount(accountMessage)
                .setAddress(addressMessage)
                .build();
        return bankAccountInfoMessage;
    }

    private List<BankAccountInfo> getBankAccountInfos(AccountTypeMessage accountTypeMessage, Flux<BankAccountInfo> accounts) {
        return accounts.toStream()
                .filter(bankAccountInfo -> bankAccountInfo.getBankAccount()
                        .getAccountType()
                        .name()
                        .equalsIgnoreCase(accountTypeMessage
                                .name())).collect(Collectors.toList());
    }

}
