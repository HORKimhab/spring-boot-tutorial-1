/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkimhab.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author H.Kimhab
 */
@Service
public class MyFirstService {

    private MyFirstClass myFirstClass;

    // @Autowired
    public MyFirstService(@Qualifier("Bean1") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "the dependency is saying: \n" + myFirstClass.sayHello();
    }
}
