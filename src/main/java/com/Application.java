package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofa.liu
 * @description
 * @create 2020/05/24 14:47
 */

@SpringBootApplication(scanBasePackages = {"com"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
