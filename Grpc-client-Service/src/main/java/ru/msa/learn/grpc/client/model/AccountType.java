package ru.msa.learn.grpc.client.model;

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
