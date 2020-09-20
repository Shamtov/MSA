package ru.msa.learn.grpcservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Table("bankaccountinfo")
public class BankAccountInfo {
    @PrimaryKeyColumn(name = "uu_id",type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;

    @Frozen
    @Column
    //@CassandraType(type = CassandraType.Name.UDT, userTypeName = "bankaccount")
    private BankAccount bankAccount;

    @Frozen
    @Column
    //@CassandraType(type = CassandraType.Name.UDT, userTypeName = "address")
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
