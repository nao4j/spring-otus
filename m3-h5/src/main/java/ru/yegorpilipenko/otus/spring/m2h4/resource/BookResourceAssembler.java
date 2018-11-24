package ru.yegorpilipenko.otus.spring.m2h4.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;
import ru.yegorpilipenko.otus.spring.m2h4.controller.CommentController;
import ru.yegorpilipenko.otus.spring.m2h4.model.Book;
import ru.yegorpilipenko.otus.spring.m2h4.model.Comment;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public final class BookResourceAssembler extends ResourceAssemblerSupport<Book, BookResource> {

    public BookResourceAssembler() {
        super(Book.class, BookResource.class);
    }

    @Override
    public BookResource toResource(final Book entity) {
        final BookResource resource
                = new BookResource(entity.getName(), entity.getAuthors(), entity.getGenres(), entity.getComments());
        final Comment exampleComment = new Comment("example@email.com", "Comment text");
        resource.add(
                linkTo(methodOn(CommentController.class).add(entity.getId(), exampleComment)).withRel("comments")
                        .withType("POST"),
                linkTo(methodOn(CommentController.class).remove(entity.getId(), exampleComment)).withRel("comments")
                        .withType("DELETE")
        );
        return resource;
    }

}
