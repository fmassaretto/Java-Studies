package com.example.demo.service;

import com.example.demo.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("When Calls GetBook Then Should Return Book")
    void whenCallsGetBookThenShouldReturnBook() {
        Book book = new Book(1L, "Book 1");

        Book result = bookService.getBooks();

        assertEquals(result.name(), book.name());
    }
}