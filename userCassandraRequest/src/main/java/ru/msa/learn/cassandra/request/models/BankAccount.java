package ru.msa.learn.cassandra.request.models;


import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table(value = "bank_accounts")
public class BankAccount {

    public BankAccount() {
    }

    @PrimaryKeyColumn(name = "uu_id", type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;

    public BankAccount(UUID uuid, String firstName, String lastName, String patronymic, long accountNumber, AccountType accountType) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    @Column(value = "first_name")
    private String firstName;
    @Column(value = "last_name")
    private String lastName;
    @Column(value = "patronymic")
    private String patronymic;
    @Column(value = "account_number")
    private long accountNumber;
    @Column(value = "account_type")
    private AccountType accountType = AccountType.CURRENT;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = uuid;
    }

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
                "uuid=" + uuid +
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
