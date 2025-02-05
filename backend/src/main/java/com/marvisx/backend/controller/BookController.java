package com.marvisx.backend.controller;

import com.marvisx.backend.dto.BookRequest;
import com.marvisx.backend.entity.Book;
import com.marvisx.backend.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class BookController {


    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(bookService.getAllBooks(page, size));
    }
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> GetBook(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBook(isbn));
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid  @RequestBody BookRequest book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(isbn, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Book>> searchBooks(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(bookService.searchBooks(query, page, size));
    }


}
