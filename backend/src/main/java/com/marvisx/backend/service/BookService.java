package com.marvisx.backend.service;

import com.marvisx.backend.dto.BookRequest;
import com.marvisx.backend.entity.Book;
import com.marvisx.backend.handleException.BookNotFoundException;
import com.marvisx.backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public Page<Book> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return bookRepository.findAll(pageable);
    }

    public Book addBook(BookRequest book) {
       Optional<Book> bookExist = bookRepository.findByIsbn(book.getIsbn());
       if(bookExist.isPresent()){
           throw new BookNotFoundException("Book Already exist ");
       }
        return bookRepository.save(mapper.toBook(book)
        );
    }

    public Book updateBook(String isbn, Book updatedBook) {
        return bookRepository.findByIsbn(isbn).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublishedDate(updatedBook.getPublishedDate());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> searchBooks(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query, pageable);
    }

    public Book getBook(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(()->new BookNotFoundException("Book with ISBN:: "+isbn+" not found"));
    }
}
