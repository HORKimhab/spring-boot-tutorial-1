package com.hkimhab.example;

/**
 *
 * @author H.Kimhab
 */
// @Component
// @Service
// @Repository
public class MyFirstClass {

  private String myVar; 

  public MyFirstClass(String myVar) {
    this.myVar = myVar;
  }
  
  public String sayHello() {
    return "-----------\nHello Universe from the MyFirstClass\nMyVar: " + myVar + "\n-----------";
  }
}
