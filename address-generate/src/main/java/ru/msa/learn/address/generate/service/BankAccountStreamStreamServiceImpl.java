package ru.msa.learn.address.generate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.StreamsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Service;
import ru.msa.learn.address.generate.models.Address;
import ru.msa.learn.address.generate.models.BankAccount;
@EnableKafkaStreams
@EnableKafka
@Service
public class BankAccountStreamStreamServiceImpl implements BankAccountStreamService<String,BankAccount> {

    Logger log = LoggerFactory.getLogger(BankAccountStreamStreamServiceImpl.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Serde<Address> addressSerde;
    @Autowired
    private PersonAddressImpl personAddress;
    @Value("${kafka.source.topic.name}")
    private String accountInputTopic;
    @Value("${kafka.address.ktable.name}")
    private String addressTable;

    @Override
    @Bean
    public KStream<String,BankAccount> executeService(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") StreamsBuilder builder) {
        KTable<String, BankAccount> inputTable = builder
                .table(accountInputTopic, Consumed.with(Serdes.String(), Serdes.String()))
                .mapValues(this::getAccountFromString);
        /*inputTable.toStream()
                .foreach((key, account) -> log.info("[Key]:" + key + " [Value]:"+ account));*/
        KStream<String, Address> addressKStream = inputTable.toStream()
                .filter((key, value) -> value.getLastName().startsWith("А")).mapValues((s, account) ->  personAddress.getRandomAddress());
       addressKStream.foreach((s, address) -> log.info(address.toString()));
       addressKStream.to(addressTable,Produced.with(Serdes.String(),addressSerde));
        return inputTable.toStream();
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

