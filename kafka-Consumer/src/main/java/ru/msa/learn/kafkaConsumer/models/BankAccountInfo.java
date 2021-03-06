package ru.msa.learn.kafkaConsumer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Table("bankaccountinfo")
public class BankAccountInfo {
    @PrimaryKeyColumn(name = "uu_id",type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;

    //@CassandraType(type = CassandraType.Name.UDT, userTypeName = "bankaccount")
    @Column
    @Frozen
    private BankAccount bankAccount;

    //@CassandraType(type = CassandraType.Name.UDT, userTypeName = "address")
    @Column
    @Frozen
    private Address address;

    public BankAccountInfo() {
    }
    public BankAccountInfo(BankAccount bankAccount, Address address) {
        this.bankAccount = bankAccount;
        this.address = address;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
