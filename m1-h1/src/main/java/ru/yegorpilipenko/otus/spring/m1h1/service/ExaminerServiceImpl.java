package ru.yegorpilipenko.otus.spring.m1h1.service;

import lombok.RequiredArgsConstructor;
import ru.yegorpilipenko.otus.spring.m1h1.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h1.domain.Question;
import ru.yegorpilipenko.otus.spring.m1h1.repository.QuestionRepository;

import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public final class ExaminerServiceImpl implements ExaminerService {

    private final QuestionRepository questionRepository;

    @Override
    public Collection<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public int validateAnswers(final Collection<Answer> answers) {
        final Map<Long, Answer> answerMap = toAnswerMap(answers);
        final Map<Long, Question> questionMap = toQuestionMap(questionRepository.findAll());
        int correctAnswerCount = 0;
        for (final Map.Entry<Long, Question> questionEntry : questionMap.entrySet()) {
            final Answer answer = answerMap.get(questionEntry.getKey());
            if (answer != null && questionEntry.getValue().getAnswer().equalsIgnoreCase(answer.getAnswerText())) {
                correctAnswerCount++;
            }
        }
        return correctAnswerCount;
    }

    private Map<Long, Answer> toAnswerMap(final Collection<Answer> answers) {
        return answers.stream().collect(toMap(Answer::getQuestionId, answer -> answer));
    }

    private Map<Long, Question> toQuestionMap(final Collection<Question> questions) {
        return questions.stream().collect(toMap(Question::getId, question -> question));
    }
}
