package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
        System.out.println("---------------------------------------------------");
        System.out.println("接口地址：http://localhost:8080/swagger-ui/index.html");
        System.out.println("---------------------------------------------------");
    }

}
