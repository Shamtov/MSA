package ru.msa.learn.kafkaConsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.msa.learn.kafkaConsumer.models.Address;
import ru.msa.learn.kafkaConsumer.models.BankAccount;
import ru.msa.learn.kafkaConsumer.models.BankAccountInfo;
import ru.msa.learn.kafkaConsumer.serde.AppSerdes;

@Service
public class BankAccountStreamService {
    private Logger log = LoggerFactory.getLogger(ConsumerKafkaImpl.class);

    @Value("${kafka.topic.name}")
    private String accountTopic;
    @Value("${kafka.address.ktable.name}")
    private String addressTopic;
    @Value("${kafka.bankAccountInfo.ktable.name}")
    private String accountInfoTopic;
    @Autowired
    private KStream<String, BankAccount> bankAccountKTable;
    @Autowired
    private KStream<String, Address> addressKTable;

    @Bean
    public KStream<String, BankAccount> getAccountsStream(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") StreamsBuilder builder) {
        KTable<String, BankAccount> inputTable = builder
                .table(accountTopic, Consumed.with(AppSerdes.String(), AppSerdes.bankAccountSerde()));
        inputTable.toStream()
                .foreach((key, account) -> log.info("Accounts[Key]:" + key + " [Value]:" + account));
        return inputTable.toStream();
    }

    @Bean
    public KStream<String, Address> getAddressStream(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") StreamsBuilder builder) {
        KTable<String, Address> inputTable = builder
                .table(addressTopic, Consumed.with(AppSerdes.String(), AppSerdes.addressSerde()));
        inputTable.toStream()
                .foreach((key, account) -> log.info("AddressStream[Key]:" + key + " [Value]:" + account));
        return inputTable.toStream();
    }

    @Bean
    public KStream<String, BankAccountInfo> joinStreams(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") StreamsBuilder builder) {
        KTable<String, BankAccountInfo> infoKTable = addressKTable.toTable()
                .join(bankAccountKTable.toTable(), (address, account) ->
                        new BankAccountInfo(account,address)
                ).mapValues((s, bankAccountInfo) -> bankAccountInfo);
        infoKTable.toStream().
                foreach((s, bankAccountInfo) -> log.info("AccountInfo[Key]:" + s + " [Value]:" + bankAccountInfo));
        infoKTable.toStream()
                .to(accountInfoTopic, Produced.with(AppSerdes.String(), AppSerdes.bankAccountInfoSerde()));
        return infoKTable.toStream();
    }
}
