package ru.msa.learn.kafkaProducer.models;

import java.util.Random;

public enum AccountType {
    CURRENT,
    SAVINGS,
    RECURRING_DEPOSIT,
    FIXED_DEPOSIT;

    public static AccountType getRandomType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
