package com.hkimhab.basic.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentMapperTest {

    @BeforeAll
    static void beforeAllTest() {
        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("From beforeAllTest");
    }

    @BeforeEach
    public void setUp() {
        // System.out.println("\n------------------------------------------------------------------------");
        System.out.println("Inside @BeforeEach");
    }

    @AfterEach
    public void afterEachTest() {
        // System.out.println("\n------------------------------------------------------------------------");
        System.out.println("From afterEachTest");
    }

    @Test
    public void testMethod1() {
        System.out.println("Output from testMethod1");
    }

    @Test
    public void testMethod2() {
        System.out.println("------------------------------------------------------------------------\n");
    }

}
