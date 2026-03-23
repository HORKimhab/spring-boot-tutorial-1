/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkimhab.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

/**
 *
 * @author H.Kimhab
 */
@Service
// @PropertySources({
//     @PropertySource("classpath:{fileNameProperties}"),
//     E.g: @PropertySource("classpath:custom.properties"),
//     @PropertySource("classpath:bar.properties")
// })

@PropertySources({
    @PropertySource("classpath:custom.properties"),
    @PropertySource("classpath:custom-file-2.properties"),
})
public class MyFirstService {

    // @Autowired
    // @Qualifier("MyFirstClass")
    private MyFirstClass myFirstClass;

    @Value("${my.custom.property: Hello HKimhab students}")
    private String customProperty;

    @Value("${my.prop: Default custom property value}")
    private String customPropertyFromOtherFile;

    @Value("321")
    private String customPropertyInt;

    @Value("${my.prop.2: Default custom property value}")
    private String customPropertyFromOtherFile2;

    // private Environment env;
    // @Autowired
    // public void injectDependencies(
    //         @Qualifier("myThirdClass") MyFirstClass myFirstClass) {
    //     this.myFirstClass = myFirstClass;
    // }
    public MyFirstService(
            @Qualifier("Bean1") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "the dependency is saying: \n" + myFirstClass.sayHello();
    }

    // public String getJavaVersion() {
    //     return "Java version: " + env.getProperty("java.version");
    // }
    // public String getOsName() {
    //     return "OS name: " + env.getProperty("os.name");
    // }
    // public String getCustomProperty() {
    //     return "Custom property: " + env.getProperty("my.custom.property");
    // }
    // @Autowired
    // public void setEnvironment(Environment env) {
    //     this.env = env;
    // }
    public String getCustomProperty() {
        return customProperty;
    }

    public String getCustomPropertyFromOtherFile() {
        return customPropertyFromOtherFile;
    }

    public String getCustomPropertyInt() {
        return customPropertyInt;
    }

    public String getCustomPropertyFromOtherFile2() {
        return customPropertyFromOtherFile2;
    }
}
