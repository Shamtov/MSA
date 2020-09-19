package ru.msa.learn.grpc.client.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.msa.learn.RequestGrpc;
import ru.msa.learn.grpc.client.model.BankAccountInfo;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GrpcConfig {

    @Value("${ru.msa.learn.grpc.host}")
    private String host;
    @Value("${ru.msa.learn.grpc.port}")
    private int port;
    @Value("${ru.msa.learn.grpc.timeout}")
    private int timeout;

    @Bean
    public ManagedChannel channel() {
        return NettyChannelBuilder.forAddress(host, port).withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS , timeout).usePlaintext().build();
    }

    @Bean
    public RequestGrpc.RequestBlockingStub blockingStub (){
        return RequestGrpc.newBlockingStub(channel());
    }

    @Bean
    @Scope("prototype")
    public List<BankAccountInfo> list(){
        return new ArrayList<>(100);
    }
}
