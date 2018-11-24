package ru.yegorpilipenko.otus.spring.m1h6.controller

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import ru.yegorpilipenko.otus.spring.m1h6.entity.BookComment
import ru.yegorpilipenko.otus.spring.m1h6.service.BookCommentService

@ExtendWith(MockitoExtension::class)
class BookCommentControllerTest : WithAssertions {

    @Mock
    lateinit var bookCommentService: BookCommentService
    lateinit var controller: BookCommentController

    @BeforeEach
    fun setup() {
        controller = BookCommentController(bookCommentService)
    }

    @Nested
    inner class BookCommentTest {

        @Test
        fun shouldPassForExistsBookComment() {
            val id = 1L
            doReturn(BookComment(id, 1, "email", "text")).whenever(bookCommentService).findById(id)
            assertThat(controller.comment(id)).isEqualTo("1: email\ntext")
        }

        @Test
        fun shouldPassForNotExistsBookComment() {
            val id = 1L
            doReturn(null).whenever(bookCommentService).findById(id)
            assertThat(controller.comment(id)).isEqualTo("Not found")
        }
    }

    @Nested
    inner class BookCommentSaveTest {

        @Test
        fun shouldPassForSaveBookComment() {
            doReturn(BookComment(1, 2, "email", "text")).whenever(bookCommentService).save(any())
            assertThat(controller.commentSave(2, "email", "text")).isEqualTo("1: email\ntext")
            verify(bookCommentService).save(BookComment(bookId = 2, email = "email", text = "text"))
        }
    }

    @Nested
    inner class BookCommentRemoveTest {

        @Test
        fun shouldPassForExistsBookComment() {
            val id = 1L
            doReturn(true).whenever(bookCommentService).removeById(id)
            assertThat(controller.commentRemove(id)).isEqualTo("Removed")
        }

        @Test
        fun shouldPassForNotExistsBookComment() {
            val id = 1L
            doReturn(false).whenever(bookCommentService).removeById(id)
            assertThat(controller.commentRemove(id)).isEqualTo("Not found")
        }
    }
}