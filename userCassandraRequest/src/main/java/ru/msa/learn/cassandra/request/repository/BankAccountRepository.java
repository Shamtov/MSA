package ru.msa.learn.cassandra.request.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.msa.learn.cassandra.request.models.BankAccount;

import java.util.UUID;

@Repository
public interface BankAccountRepository extends ReactiveCassandraRepository<BankAccount, UUID> {
    @AllowFiltering
    Mono<BankAccount> findByUuid(UUID uuid);
}
