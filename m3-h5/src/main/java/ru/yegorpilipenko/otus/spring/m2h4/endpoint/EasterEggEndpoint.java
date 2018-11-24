package ru.yegorpilipenko.otus.spring.m2h4.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Service;

@Service
@Endpoint(id = "egg")
public class EasterEggEndpoint {

    @ReadOperation
    public String egg() {
        return "42";
    }

}
