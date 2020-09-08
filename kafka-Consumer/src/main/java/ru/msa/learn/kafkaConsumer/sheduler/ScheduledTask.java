package ru.msa.learn.kafkaConsumer.sheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.msa.learn.kafkaConsumer.models.BankAccount;
import ru.msa.learn.kafkaConsumer.service.BankAccountServiceImpl;

import java.util.List;

@Component
public class ScheduledTask {
    private Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private List<BankAccount> accounts;
    @Autowired
    private BankAccountServiceImpl accountService;

    @Scheduled(fixedDelayString = "${sheduler.delay}")
    @Transactional
    void saveAccounts() {
        if (accounts.size() > 0) {
            accountService.saveAllAccounts(accounts);
            accounts.clear();
        }
    }

}
