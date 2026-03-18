package com.hkimhab.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    // By Bean 
    @Bean("MyFirstClass")
    @Qualifier("Bean1")
    public MyFirstClass myFirstClass() {
        return new MyFirstClass("First bean");
    }

    @Bean
    @Qualifier("Bean2")
    public MyFirstClass mySecondClass() {
        return new MyFirstClass("Seconde bean");
    }
}
