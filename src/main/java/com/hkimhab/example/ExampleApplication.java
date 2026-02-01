package com.hkimhab.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		// SpringApplication.run(ExampleApplication.class, args);

		// MyFirstClass myFirstClass = new MyFirstClass();
		// System.out.println(myFirstClass.sayHello());


		// By Bean 
		var ctx = SpringApplication.run(ExampleApplication.class, args);

		MyFirstClass myFirstClass = ctx.getBean(MyFirstClass.class); 
		System.out.println(myFirstClass.sayHello());
	}

	// By Bean 
	// @Bean 
	public MyFirstClass myFirstClass(){
		return new MyFirstClass();
	}

}
