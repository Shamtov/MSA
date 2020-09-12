package ru.msa.learn.kafkaConsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.msa.learn.kafkaConsumer.models.BankAccount;

import java.util.List;

@Service
public class ConsumerKafkaImpl implements ConsumerKafka<BankAccount> {

    private Logger log = LoggerFactory.getLogger(ConsumerKafkaImpl.class);
    @Autowired
    private List<BankAccount> accounts;

    @Override
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "#'${kafka.group.id}'")
    public void receiveMessage(BankAccount bankAccount) {
        try {
            accounts.add(bankAccount);
        } catch (RuntimeException e) {
            log.error("Unable to save data in Cassandra due to : " + e.getMessage());
        }
    }
}
