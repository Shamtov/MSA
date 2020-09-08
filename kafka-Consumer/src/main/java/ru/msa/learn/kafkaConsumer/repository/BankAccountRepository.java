package ru.msa.learn.kafkaConsumer.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import ru.msa.learn.kafkaConsumer.models.BankAccount;

import java.util.UUID;

@Repository
public interface BankAccountRepository extends ReactiveCassandraRepository<BankAccount, UUID> {
}
