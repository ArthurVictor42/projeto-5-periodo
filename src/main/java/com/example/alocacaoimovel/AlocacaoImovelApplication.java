package com.example.alocacaoimovel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlocacaoImovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlocacaoImovelApplication.class, args);
    }
}
