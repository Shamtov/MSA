package ru.msa.learn.kafkaProducer.service;


import ru.msa.learn.kafkaProducer.models.BankAccount;

public interface ProduceKafka {
    void sendMessage(BankAccount bankAccount);
}
