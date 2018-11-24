package ru.yegorpilipenko.otus.spring.m1h4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.yegorpilipenko.otus.spring.m1h4.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h4.domain.Question;
import ru.yegorpilipenko.otus.spring.m1h4.service.ExaminerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Long.parseLong;
import static java.lang.System.out;
import static java.util.stream.Collectors.toMap;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public final class ExaminerController {

    private static final String OPTION_TEMPLATE = "%d) %s%n";

    private final ExaminerService examinerService;
    private final Locale locale;
    private final MessageSource messageSource;
    private final Scanner scanner;

    @ShellMethod(value = "Start quiz.", group = "Exam Commands")
    public String quiz(
            @ShellOption(help = "Student first name.") String firstName,
            @ShellOption(help = "Student last name.") String lastName
    ) {
        final Collection<Question> questions = examinerService.getQuestions();
        final Collection<Answer> answers = getAnswers(scanner, questions);
        final int result = examinerService.validateAnswers(answers);
        return getLocalized("dialog.result", firstName, lastName, result, questions.size());
    }

    private Collection<Answer> getAnswers(final Scanner scanner, final Collection<Question> questions) {
        final Collection<Answer> answers = new ArrayList<>();
        for (final Question question : questions) {
            out.println(getLocalized(question.getFormulation()));
            final Map<Long, Answer> options = question.getOptions().stream()
                    .collect(toMap(Answer::getId, option -> option));
            for (final Map.Entry<Long, Answer> entry : options.entrySet()) {
                out.printf(OPTION_TEMPLATE, entry.getKey(), getLocalized(entry.getValue().getAnswerText()));
            }
            try {
                final long optionId = parseLong(scanner.next());
                if (0 < optionId && optionId < 4) {
                    answers.add(options.get(optionId));
                }
            } catch (final NumberFormatException e) {
                log.error("Incorrect input");
            }
        }
        return answers;
    }

    private String getLocalized(final String key, final Object... args) {
        return messageSource.getMessage(key, args, locale);
    }
}
