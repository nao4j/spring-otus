package ru.yegorpilipenko.otus.spring.m2h2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.yegorpilipenko.otus.spring.m2h2.document.Comment;
import ru.yegorpilipenko.otus.spring.m2h2.service.CommentService;

import javax.annotation.Nonnull;

import static java.lang.String.format;

@ShellComponent
@RequiredArgsConstructor
public final class CommentController extends AbstractShellController {

    @Nonnull
    private final CommentService commentService;

    @Nonnull
    @ShellMethod(key = "comment-add", value = "Add comment to book.", group = "Comment Commands")
    public String save(@Nonnull String bookId, @Nonnull String email, @Nonnull String text) {
        final Comment comment = new Comment(email, text);
        return toView(commentService.add(bookId, comment));
    }

    @Nonnull
    @ShellMethod(key = "comment-remove", value = "Remove comment by ID.", group = "Comment Commands")
    public String remove(@Nonnull String bookId, @Nonnull String email, @Nonnull String text) {
        final Comment comment = new Comment(email, text);
        return commentService.remove(bookId, comment) ? REMOVE_SUCCESS_MESSAGE : NOT_FOUND_MESSAGE;
    }

    @Nonnull
    private String toView(@Nonnull Comment comment) {
        return format("%s\n%s", comment.getEmail(), comment.getText());
    }

}
