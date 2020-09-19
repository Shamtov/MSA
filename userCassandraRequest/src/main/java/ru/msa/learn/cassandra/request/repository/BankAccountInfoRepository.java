package ru.msa.learn.cassandra.request.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.msa.learn.cassandra.request.models.BankAccountInfo;

import java.util.UUID;

@Repository
public interface BankAccountInfoRepository extends ReactiveCassandraRepository<BankAccountInfo, UUID> {
    Mono<BankAccountInfo> findByUuid(UUID uuid);
}
