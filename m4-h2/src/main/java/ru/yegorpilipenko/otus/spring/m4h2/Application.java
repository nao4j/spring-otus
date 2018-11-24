package ru.yegorpilipenko.otus.spring.m4h2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import static org.springframework.boot.SpringApplication.run;

@EnableCircuitBreaker
@SpringBootApplication
@EnableHystrixDashboard
public class Application {

    public static void main(final String... args) {
        run(Application.class, args);
    }

}
