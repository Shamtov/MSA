package ru.msa.learn.address.generate.service;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

public interface BankAccountStreamService<K,V> {

    KStream<K,V> executeService(StreamsBuilder streamsBuilder);
}
