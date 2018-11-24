package ru.yegorpilipenko.otus.spring.m2h4.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yegorpilipenko.otus.spring.m2h4.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

}
