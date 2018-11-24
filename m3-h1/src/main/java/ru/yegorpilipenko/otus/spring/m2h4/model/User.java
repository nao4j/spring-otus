package ru.yegorpilipenko.otus.spring.m2h4.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public final class User {

    @Id
    private final String id;
    private final String username;
    private final String password;

}
