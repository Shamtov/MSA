package ru.msa.learn.kafkaConsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.msa.learn.kafkaConsumer.models.BankAccount;

@Service
public class ConsumerKafkaImpl implements ConsumerKafka {

    private Logger log = LoggerFactory.getLogger(ConsumerKafkaImpl.class);

    @Override
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "#'${kafka.group.id}'")
    public void receiveMessage(BankAccount bankAccount) {
        log.info(bankAccount.toString());
    }
}
