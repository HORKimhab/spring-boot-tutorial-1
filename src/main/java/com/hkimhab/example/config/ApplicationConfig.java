package com.hkimhab.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hkimhab.example.MyFirstClass;

@Configuration
// @Profile("dev")
public class ApplicationConfig {

    // By Bean 
    @Bean("MyFirstClass")
    @Qualifier("Bean1")
    public MyFirstClass myFirstClass() {
        return new MyFirstClass("First bean");
    }

    // @Bean
    // // @Qualifier("Bean2")
    // public MyFirstClass mySecondClass() {
    //     return new MyFirstClass("Second bean");
    // }
    // @Bean
    // // @Primary
    // // @Qualifier("Bean2")
    // public MyFirstClass myThirdClass() {
    //     return new MyFirstClass("Third bean");
    // }
}
