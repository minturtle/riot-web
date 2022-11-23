package com.riot.web.config;


import com.RiotAPIConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Value("${riot.token}")
    private String token;
    private RiotAPIConnection conn = new RiotAPIConnection(token);

    @Bean
    public RiotAPIConnection connection(){
        return conn;
    }
}
