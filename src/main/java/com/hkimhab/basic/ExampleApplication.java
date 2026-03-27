package com.hkimhab.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        // // SpringApplication.run(ExampleApplication.class, args);

		// var app = new SpringApplication(ExampleApplication.class);
		// // Customize set active profile
		// app.setAdditionalProfiles("dev");

        // // MyFirstClass myFirstClass = new MyFirstClass();
        // // System.out.println(myFirstClass.sayHello());
        // // By Bean 
        // var ctx = app.run(args);

        // // MyFirstClass myFirstClass = ctx.getBean("MyFirstClass", MyFirstClass.class); 
        // // System.out.println(myFirstClass.sayHello());
        // MyFirstService myFirstService = ctx.getBean(MyFirstService.class);
        // System.out.println(myFirstService.tellAStory());
        // // System.out.println(myFirstService.getJavaVersion());
        // // System.out.println(myFirstService.getOsName());
        // // System.out.println(myFirstService.getCustomProperty());
        // System.out.println(myFirstService.getCustomProperty());
        // System.out.println(myFirstService.getCustomPropertyFromOtherFile());
        // System.out.println(myFirstService.getCustomPropertyInt());
        // System.out.println(myFirstService.getCustomPropertyFromOtherFile2());

		// Lesson: REST
		SpringApplication.run(ExampleApplication.class, args);
    }

}
