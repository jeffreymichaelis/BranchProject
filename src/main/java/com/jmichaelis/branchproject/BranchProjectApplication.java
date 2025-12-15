package com.jmichaelis.branchproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BranchProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BranchProjectApplication.class, args);
    }

}
