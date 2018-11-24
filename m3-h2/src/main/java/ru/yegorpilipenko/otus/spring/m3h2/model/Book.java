package ru.yegorpilipenko.otus.spring.m3h2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Value
@Builder
@Document(collection = "books")
public final class Book {

    @Id @NonFinal String id;
    @NonNull String name;
    @NonNull Collection<Author> authors;
    @NonNull Collection<Genre> genres;
    @NonNull Collection<Comment> comments;

    @JsonCreator
    private Book(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("authors") Collection<Author> authors,
            @JsonProperty("genres") Collection<Genre> genres,
            @JsonProperty("comments") Collection<Comment> comments
    ) {
        this.id = id;
        this.name = name;
        this.authors = authors != null ? List.copyOf(authors) : List.of();
        this.genres = genres != null ? List.copyOf(genres) : List.of();
        this.comments = comments != null ? List.copyOf(comments) : List.of();
    }

}
