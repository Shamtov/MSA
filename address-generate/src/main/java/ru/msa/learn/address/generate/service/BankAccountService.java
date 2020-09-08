package ru.msa.learn.address.generate.service;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

public interface BankAccountService {
    KStream<?,?> execute(StreamsBuilder builder);
}
