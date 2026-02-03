package com.hkimhab.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    // By Bean 
	@Bean("MyFirstClass")
	public MyFirstClass myFirstClass(){
		return new MyFirstClass("First bean");
	}
}
