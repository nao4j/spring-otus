package ru.yegorpilipenko.otus.spring.m3h3.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookService<B> {

    fun getPage(pageable: Pageable): Page<B>

}
