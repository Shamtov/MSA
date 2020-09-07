package ru.msa.learn.kafkaProducer.sheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.msa.learn.kafkaProducer.models.BankAccount;
import ru.msa.learn.kafkaProducer.service.BankAccountServiceImpl;


@Component
public class ScheduledTasks {
    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Autowired
    private RestTemplate restTemplate;
    @Value("${bankAccountURL}")
    private String strinURL;
    @Autowired
    BankAccountServiceImpl accountService;

    @Scheduled(fixedDelayString = "${sheduler.delay}")
    void getAccounts() {
        BankAccount bankAccount = restTemplate.getForObject(strinURL, BankAccount.class);
        accountService.execute(bankAccount);
        logger.info(bankAccount.toString());
    }
}
