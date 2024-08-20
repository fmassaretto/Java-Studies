package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookControllerTest {

    @InjectMocks
    private BookController controller;

    @Mock
    private BookService bookService;

    @Test
    void book() {
        Book book = new Book(1L, "Book 1");

        when(bookService.getBooks()).thenReturn(book);

        Book result = controller.book();

        assertEquals(book.id(), result.id());
    }
}