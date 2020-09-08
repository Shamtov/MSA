package ru.msa.learn.kafkaProducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.msa.learn.kafkaProducer.models.BankAccount;

@Service
public class ProduceKafkaImpl implements ProduceKafka {

    private Logger logger = LoggerFactory.getLogger(ProduceKafkaImpl.class);
    @Autowired
    private KafkaTemplate<String, BankAccount> kafkaTemplate;
    @Value("${kafka.topic.name}")
    private String topic;

    @Override
    public void sendMessage(BankAccount bankAccount) {
        ListenableFuture<SendResult<String, BankAccount>> future = kafkaTemplate.send(topic, bankAccount);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, BankAccount> result) {
                logger.info("Sent message=[" + bankAccount +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Unable to send message=["
                        + bankAccount + "] due to : " + throwable.getMessage());
            }
        });
    }
}
