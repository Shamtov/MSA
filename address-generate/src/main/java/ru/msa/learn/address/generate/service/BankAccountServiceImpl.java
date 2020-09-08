package ru.msa.learn.address.generate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Service;
import ru.msa.learn.address.generate.models.BankAccount;

@EnableKafkaStreams
@EnableKafka
@Service
public class BankAccountServiceImpl implements BankAccountService {

    Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Serde<BankAccount> accountSerde;
    @Value("${kafka.source.topic.name}")
    private String inputTopic;
    @Value("${kafka.output.topic.name}")
    private String outputTopic;


    @Override
    @Bean
    public KStream<String, BankAccount> execute(StreamsBuilder kStreamBuilder) {
        KStream<String, String> inputStream = kStreamBuilder
                .stream(inputTopic, Consumed.with(Serdes.String(), Serdes.String()));
        KStream<String, BankAccount> bankAccountKStream = inputStream.mapValues(this::getAccountFromString).filter((key, value) -> value.getLastName().startsWith("А"));
        bankAccountKStream.foreach((s, bankAccount) -> log.info(bankAccount.toString()));
        return bankAccountKStream;
    }

    private BankAccount getAccountFromString(String accountsString) {
        BankAccount account = null;
        try {
            account = objectMapper.readValue(accountsString, BankAccount.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return account;
    }
}

