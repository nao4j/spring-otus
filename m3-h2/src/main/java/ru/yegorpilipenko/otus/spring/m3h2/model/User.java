package ru.yegorpilipenko.otus.spring.m3h2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "users")
public final class User {

    public static class Role {

        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";

        private Role() {
            throw new UnsupportedOperationException();
        }

    }

    @Id
    private final String id;
    private final String username;
    private final String password;
    private final Set<String> roles;

}
