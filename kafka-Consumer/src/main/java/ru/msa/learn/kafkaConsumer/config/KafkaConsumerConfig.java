package ru.msa.learn.kafkaConsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.msa.learn.kafkaConsumer.models.BankAccount;
import ru.msa.learn.kafkaConsumer.models.BankAccountInfo;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;
    @Value("${kafka.group.id}")
    private String kafkaGroupId;

    @Bean
    ConsumerFactory<String, BankAccount> bankAccountConsumerConfig() {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer<BankAccount> deserializer = new JsonDeserializer<>(BankAccount.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    ConsumerFactory<String, BankAccountInfo> bankAccountInfoConsumerConfig() {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer<BankAccountInfo> deserializer = new JsonDeserializer<>(BankAccountInfo.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BankAccount>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, BankAccount> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bankAccountConsumerConfig());
        return factory;
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BankAccountInfo>
    accountInfoListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, BankAccountInfo> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bankAccountInfoConsumerConfig());
        return factory;
    }
}
