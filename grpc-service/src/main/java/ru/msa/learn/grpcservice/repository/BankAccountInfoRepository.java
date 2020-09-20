package ru.msa.learn.grpcservice.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import ru.msa.learn.grpcservice.model.BankAccountInfo;

import java.util.UUID;

@Repository
public interface BankAccountInfoRepository extends ReactiveCassandraRepository<BankAccountInfo, UUID> {
}
