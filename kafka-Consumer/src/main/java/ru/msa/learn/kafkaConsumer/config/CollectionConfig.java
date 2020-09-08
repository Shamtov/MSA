package ru.msa.learn.kafkaConsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.msa.learn.kafkaConsumer.models.BankAccount;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class CollectionConfig {
    @Bean
    public List<BankAccount> accounts() {
        return new CopyOnWriteArrayList<>();
    }
}
