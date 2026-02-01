package com.hkimhab.example;

import org.springframework.stereotype.Service;

/**
 *
 * @author H.Kimhab
 */
// @Component
@Service
public class MyFirstClass {
  
  public String sayHello() {
    return "Hello Universe from the MyFirstClass";
  }
}
