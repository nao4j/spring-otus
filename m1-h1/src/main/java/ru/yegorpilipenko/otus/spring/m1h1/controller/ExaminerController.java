package ru.yegorpilipenko.otus.spring.m1h1.controller;

import lombok.RequiredArgsConstructor;
import ru.yegorpilipenko.otus.spring.m1h1.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h1.domain.Question;
import ru.yegorpilipenko.otus.spring.m1h1.domain.Student;
import ru.yegorpilipenko.otus.spring.m1h1.service.ExaminerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static java.lang.System.out;

@RequiredArgsConstructor
public final class ExaminerController {

    private static final String FIRST_NAME_QUESTION = "Как Ваше имя?";
    private static final String LAST_NAME_QUESTION = "Как Ваша фамилия?";
    private static final String OPTION_TEMPLATE = "* %s%n";
    private static final String RESULT_TEMPLATE = "%s %s, Ваш результат: %d из %d%n";

    private final ExaminerService examinerService;

    public void show() {
        try (final Scanner scanner = new Scanner(System.in)) {
            final Student student = getStudent(scanner);
            final Collection<Question> questions = examinerService.getQuestions();
            final Collection<Answer> answers = getAnswers(scanner, questions);
            final int result = examinerService.validateAnswers(answers);
            printResult(student, questions, result);
        }
    }

    private Student getStudent(final Scanner scanner) {
        out.println(FIRST_NAME_QUESTION);
        final String studentFirstName = scanner.next();

        out.println(LAST_NAME_QUESTION);
        final String studentSecondName = scanner.next();

        return new Student(studentFirstName, studentSecondName);
    }

    private Collection<Answer> getAnswers(final Scanner scanner, final Collection<Question> questions) {
        final Collection<Answer> answers = new ArrayList<>();
        for (final Question question : questions) {
            out.println(question.getFormulation());
            for (final String option : question.getOptions()) {
                out.printf(OPTION_TEMPLATE, option);
            }
            answers.add(new Answer(question.getId(), scanner.next()));
        }
        return answers;
    }

    private void printResult(final Student student, final Collection<Question> questions, final int result) {
        final String firstName = student.getFirstName();
        final String lastName = student.getLastName();
        out.printf(RESULT_TEMPLATE, firstName, lastName, result, questions.size());
    }
}
