package ru.yegorpilipenko.otus.spring.m2h5.controller

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yegorpilipenko.otus.spring.m2h5.domain.Book
import ru.yegorpilipenko.otus.spring.m2h5.service.BookService

@WebFluxTest(BookController::class)
@ExtendWith(SpringExtension::class)
class BookControllerTest: WithAssertions {

    @Autowired
    lateinit var webClient: WebTestClient

    @MockBean
    lateinit var bookService: BookService

    @AfterEach
    fun clean() {
        // workaround for nested classes
        Mockito.reset(bookService)
    }

    @Nested
    inner class GetAllTest {

        @Test
        fun shouldPassForExists() {
            val book1 = Book("1", "book1")
            val book2 = Book("2", "book2")
            doReturn(Flux.just(book1, book2)).whenever(bookService).getAll()
            webClient.get().uri("/books").accept(MediaType.APPLICATION_JSON_UTF8)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBodyList(Book::class.java)
                    .hasSize(2)
                    .contains(book1, book2)
            verify(bookService).getAll()
            verifyNoMoreInteractions(bookService)
        }

        @Test
        fun shouldPassForNotExists() {
            doReturn(Flux.empty<Book>()).whenever(bookService).getAll()
            webClient.get().uri("/books")
                    .exchange()
                    .expectStatus().isOk()
            verify(bookService).getAll()
            verifyNoMoreInteractions(bookService)
        }

    }

    @Nested
    inner class GetByIdTest {

        @Test
        fun shouldPassForExists() {
            val id = "1"
            val book = Book(id, "book1")
            doReturn(Mono.just(book)).whenever(bookService).getById(id)
            webClient.get().uri("/books/{id}", book.id).accept(MediaType.APPLICATION_JSON_UTF8)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.id").isEqualTo(id)
                    .jsonPath("$.name").isEqualTo(book.name)
            verify(bookService).getById(id)
            verifyNoMoreInteractions(bookService)
        }

        @Test
        fun shouldPassForNotExists() {
            val id = "1"
            doReturn(Mono.empty<Book>()).whenever(bookService).getById(id)
            webClient.get().uri("/books/{id}", id)
                    .exchange()
                    .expectStatus().isOk()
            verify(bookService).getById(id)
            verifyNoMoreInteractions(bookService)
        }

    }

    @Nested
    inner class SaveTest {

        @Test
        fun shouldPass() {
            val id = "1"
            val book = Book(id, "book1")
            doReturn(Mono.just(book)).whenever(bookService).save(book)
            webClient.post().uri("/books").accept(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(book))
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.id").isEqualTo(id)
                    .jsonPath("$.name").isEqualTo(book.name)
            verify(bookService).save(book)
            verifyNoMoreInteractions(bookService)
        }

    }

    @Nested
    inner class RemoveByIdTest {

        @Test
        fun shouldPass() {
            val id = "1"
            doReturn(Mono.empty<Void>()).whenever(bookService).removeById(id)
            webClient.delete().uri("/books/{id}", id).accept(MediaType.APPLICATION_JSON_UTF8)
                    .exchange()
                    .expectStatus().isOk()
            verify(bookService).removeById(id)
            verifyNoMoreInteractions(bookService)
        }

    }

}
