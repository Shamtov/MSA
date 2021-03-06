package ru.msa.learn.grpc.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BankAccountInfo {
    private UUID uuid;
    private BankAccount bankAccount;
    private Address address;

    public BankAccountInfo() {
    }

    public BankAccountInfo(UUID uuid, BankAccount bankAccount, Address address) {
        this.uuid = uuid;
        this.bankAccount = bankAccount;
        this.address = address;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
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
                "uuid=" + uuid +
                ", bankAccount=" + bankAccount +
                ", address=" + address +
                '}';
    }
}
