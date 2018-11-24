package ru.yegorpilipenko.otus.spring.m3h3.config

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.data.MongoItemReader
import org.springframework.batch.item.data.RepositoryItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.mongodb.core.MongoTemplate
import ru.yegorpilipenko.otus.spring.m3h3.document.Book
import ru.yegorpilipenko.otus.spring.m3h3.repository.BookJpaRepository

@Configuration
@EnableBatchProcessing
class BatchConfig {

    @Bean
    fun mongoItemReader(mongoTemplate: MongoTemplate): ItemReader<Book> {
        val reader = MongoItemReader<Book>()
        reader.setCollection("books")
        reader.setTargetType(Book::class.java)
        reader.setTemplate(mongoTemplate)
        reader.setQuery("{}")
        reader.setSort(mapOf(Pair("name", ASC)))
        return reader
    }

    @Bean
    fun step1(
            builder: StepBuilderFactory,
            reader: ItemReader<Book>,
            writer: ItemWriter<ru.yegorpilipenko.otus.spring.m3h3.entity.Book>
    ): Step = builder.get("step1")
            .chunk<Book, ru.yegorpilipenko.otus.spring.m3h3.entity.Book>(5)
            .reader(reader)
            .processor(ItemProcessor<Book, ru.yegorpilipenko.otus.spring.m3h3.entity.Book> {
                book -> ru.yegorpilipenko.otus.spring.m3h3.entity.Book(name = book.name)
            }).writer(writer)
            .build()

    @Bean
    fun dbItemWriter(bookJpaRepository: BookJpaRepository): ItemWriter<ru.yegorpilipenko.otus.spring.m3h3.entity.Book> {
        val writer = RepositoryItemWriter<ru.yegorpilipenko.otus.spring.m3h3.entity.Book>()
        writer.setRepository(bookJpaRepository)
        writer.setMethodName("save")
        return writer
    }

    @Bean
    fun migrateToDbJob(jobBuilderFactory: JobBuilderFactory, @Qualifier("step1") step1: Step): Job
            = jobBuilderFactory.get("migrateToDbJob").flow(step1).end().build()

}
