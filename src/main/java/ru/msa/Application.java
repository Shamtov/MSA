package ru.msa;

import java.io.IOException;
import java.util.UUID;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println(new BankAccount().build());
            System.out.println(new BankAccount().build());
            System.out.println(new BankAccount().build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
