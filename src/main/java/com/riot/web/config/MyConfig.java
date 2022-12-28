package com.riot.web.config;


import com.RiotAPIConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MyConfig {


    @Autowired
    private final Environment env;

    private RiotAPIConnection conn;

    public MyConfig(Environment env) {
        this.env = env;
        conn = new RiotAPIConnection(env.getProperty("riot.token"));
    }

    @Bean
    public RiotAPIConnection connection(){
        return conn;
    }
}
