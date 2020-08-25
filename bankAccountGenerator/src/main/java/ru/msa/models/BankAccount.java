package ru.msa.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.msa.impl.PersonImpl;

import java.util.Objects;
import java.util.UUID;

@Component
public class BankAccount {

    public BankAccount() {
    }

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String patronymic;
    private long accountNumber;
    private Person gender;
    @Autowired
    private PersonImpl personImpl;

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

    @Override
    public String toString() {
        return "BankAccount{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", accountNumber=" + accountNumber +
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

    public BankAccount build() {
        this.uuid = UUID.randomUUID();
        this.gender = personImpl.getRandomGender();
        this.firstName = personImpl.getRandomName(this.gender);
        this.lastName = personImpl.getRandomSurname(this.gender);
        this.patronymic = personImpl.getPatronymics(this.gender);
        this.accountNumber = Person.getRandomNum(Long.MAX_VALUE);
        return this;
    }

}
