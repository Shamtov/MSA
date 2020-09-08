package ru.msa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msa.models.BankAccount;

@RestController
public class BankAccountController {
    @Autowired
    private BankAccount bankAccount;
    @GetMapping("account")
    public BankAccount getAccount() {
        return bankAccount.build();
    }
}
