package ru.msa.learn.kafkaConsumer.service;


import ru.msa.learn.kafkaConsumer.models.BankAccount;

public interface ConsumerKafka {
    void receiveMessage(BankAccount bankAccount);
}
