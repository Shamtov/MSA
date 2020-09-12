package ru.msa.learn.kafkaConsumer.serde;

import ru.msa.learn.kafkaConsumer.models.Address;
import ru.msa.learn.kafkaConsumer.models.BankAccount;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import ru.msa.learn.kafkaConsumer.models.BankAccountInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Application level Serdes
 *
 * @author prashant
 * @author www.learningjournal.guru
 */
public class AppSerdes extends Serdes {

    static final class BankAccountSerde extends WrapperSerde<BankAccount> {
        public BankAccountSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<BankAccount> bankAccountSerde() {
        BankAccountSerde serde = new BankAccountSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", BankAccount.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class AddressSerde extends WrapperSerde<Address> {
        AddressSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Address> addressSerde() {
        AddressSerde serde = new AddressSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", Address.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class BankAccountInfoSerde extends WrapperSerde<BankAccountInfo> {
        BankAccountInfoSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<BankAccountInfo> bankAccountInfoSerde() {
        BankAccountInfoSerde serde = new BankAccountInfoSerde();
        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", BankAccountInfo.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
