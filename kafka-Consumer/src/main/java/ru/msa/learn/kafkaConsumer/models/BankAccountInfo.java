package ru.msa.learn.kafkaConsumer.models;

import java.util.Objects;

public class BankAccountInfo {
    private BankAccount bankAccount;
    private Address address;

    public BankAccountInfo() {
    }
    public BankAccountInfo(BankAccount bankAccount, Address address) {
        this.bankAccount = bankAccount;
        this.address = address;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountInfo that = (BankAccountInfo) o;
        return Objects.equals(bankAccount, that.bankAccount) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccount, address);
    }

    @Override
    public String toString() {
        return "BankAccountInfo{" +
                "bankAccount=" + bankAccount +
                ", address=" + address +
                '}';
    }
}
