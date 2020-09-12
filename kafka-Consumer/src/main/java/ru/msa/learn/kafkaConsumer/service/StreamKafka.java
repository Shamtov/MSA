package ru.msa.learn.kafkaConsumer.service;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

public interface StreamKafka<T,K,A> {
    KStream<K,T> getAccountsStream(StreamsBuilder builder);
    KStream<K,A> getAddressStream(StreamsBuilder builder);
    KStream<K,A> joinStreams(StreamsBuilder builder);
}
