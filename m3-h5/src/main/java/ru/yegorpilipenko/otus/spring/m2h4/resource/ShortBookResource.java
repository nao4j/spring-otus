package ru.yegorpilipenko.otus.spring.m2h4.resource;

import lombok.NonNull;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;

@Value
public final class ShortBookResource extends ResourceSupport {

    @NonNull String name;

}
