package com.jpa;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HickariCPApplication {

    private static final ExecutorService executor = Executors.newFixedThreadPool(25);

    @Autowired
    private ApplicationContext applicationContext;    

    public static void main(String[] args) {
        SpringApplication.run(HickariCPApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            while(true) {
                SampleRepository sampleThread
                        = applicationContext.getBean(SampleRepository.class);
                executor.execute(sampleThread);
                
                Thread.sleep(new Random().nextInt(125));
            }

        };
    }
}
