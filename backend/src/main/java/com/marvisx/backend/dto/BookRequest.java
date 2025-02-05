package com.marvisx.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Author is required")
    private String author;
    @NotNull(message = "Isbn is required")
    @Column(name = "isbn", unique = true)
    private String isbn;
    @NotNull(message = "PublishedDate is required")
    private LocalDate publishedDate;
}
