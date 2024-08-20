package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public Book getBooks(){
        return new Book(1, "Book 1");
    }
}
