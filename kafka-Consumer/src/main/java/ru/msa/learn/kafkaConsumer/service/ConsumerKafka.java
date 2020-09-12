package ru.msa.learn.kafkaConsumer.service;


public interface ConsumerKafka<T> {
    void receiveMessage(T t);
}
