package ru.msa.learn.cassandra.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import ru.msa.learn.cassandra.request.models.BankAccountInfo;

@SpringBootApplication
@EnableReactiveCassandraRepositories(basePackages = "ru.msa.learn.cassandra.request.repository")
@EntityScan(basePackageClasses = BankAccountInfo.class)
public class UserCassandraRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCassandraRequestApplication.class, args);
	}

}
