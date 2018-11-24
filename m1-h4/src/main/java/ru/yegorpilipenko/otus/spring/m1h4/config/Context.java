package ru.yegorpilipenko.otus.spring.m1h4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Scanner;

@Configuration
public class Context {

    @Bean
    public Locale locale(@Value("${locale}") final String locale) {
        final String[] parts = locale.split("_");
        final String language = parts[0];
        final String country = parts[1];
        return new Locale(language, country);
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("/i18n/lang");
        return messageSource;
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
