package ru.msa.learn.grpcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@SpringBootApplication
@EnableReactiveCassandraRepositories
public class GrpcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcServiceApplication.class, args);
	}

}
