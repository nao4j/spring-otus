package ru.yegorpilipenko.otus.spring.m1h4.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.yegorpilipenko.otus.spring.m1h4.domain.Answer;
import ru.yegorpilipenko.otus.spring.m1h4.domain.Question;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.HashSet;

import static java.lang.Long.parseLong;
import static java.util.Arrays.asList;
import static org.apache.commons.csv.CSVFormat.DEFAULT;
import static org.springframework.util.ResourceUtils.getFile;
import static ru.yegorpilipenko.otus.spring.m1h4.repository.QuestionSchema.ANSWER;
import static ru.yegorpilipenko.otus.spring.m1h4.repository.QuestionSchema.FORMULATION;
import static ru.yegorpilipenko.otus.spring.m1h4.repository.QuestionSchema.OPTION_1;
import static ru.yegorpilipenko.otus.spring.m1h4.repository.QuestionSchema.OPTION_2;
import static ru.yegorpilipenko.otus.spring.m1h4.repository.QuestionSchema.OPTION_3;

@Repository
public final class QuestionCsvRepository implements QuestionRepository {

    private static final CSVFormat FORMAT = DEFAULT.withHeader(QuestionSchema.class).withSkipHeaderRecord(true);

    private final Collection<Question> cache = new HashSet<>();

    public QuestionCsvRepository(@Value("${csv.questions}") final String questionCsvUri) throws IOException {
        try (final Reader reader = new FileReader(getFile(questionCsvUri));
             final CSVParser parser = FORMAT.parse(reader)) {
            int nextId = 0;
            for (final CSVRecord record : parser.getRecords()) {
                cache.add(toQuestion(nextId++, record));
            }
        }
    }

    @Override
    public Collection<Question> findAll() {
        return new HashSet<>(cache);
    }

    private Question toQuestion(final int id, final CSVRecord record) {
        return new Question(
                id,
                record.get(FORMULATION),
                parseLong(record.get(ANSWER)),
                asList(
                        new Answer(1, id, record.get(OPTION_1)),
                        new Answer(2, id, record.get(OPTION_2)),
                        new Answer(3, id, record.get(OPTION_3))
                )
        );
    }
}
