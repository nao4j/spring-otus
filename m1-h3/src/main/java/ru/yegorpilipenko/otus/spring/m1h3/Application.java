package ru.yegorpilipenko.otus.spring.m1h3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.yegorpilipenko.otus.spring.m1h3.controller.ExaminerController;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class Application {

    public static void main(final String... args) {
        final ConfigurableApplicationContext context = run(Application.class, args);
        final ExaminerController controller = context.getBean(ExaminerController.class);
        controller.show();
    }
}
