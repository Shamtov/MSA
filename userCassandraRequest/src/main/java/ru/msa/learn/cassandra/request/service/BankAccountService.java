package ru.msa.learn.cassandra.request.service;

import reactor.core.publisher.Mono;
import ru.msa.learn.cassandra.request.models.BankAccount;

import java.util.UUID;

public interface BankAccountService {
    Mono<BankAccount> getAccountByUuid(UUID uuid);
}
