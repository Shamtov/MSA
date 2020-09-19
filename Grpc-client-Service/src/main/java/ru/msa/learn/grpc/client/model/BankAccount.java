package ru.msa.learn.grpc.client.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BankAccount {

    public BankAccount() {
    }

    public BankAccount(String firstName, String lastName, String patronymic, long accountNumber, AccountType accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    private String firstName;
    private String lastName;
    private String patronymic;
    private long accountNumber;
    private AccountType accountType = AccountType.CURRENT;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountType=" + accountType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountNumber == that.accountNumber &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, accountNumber);
    }

}
