package com.marvisx.backend.service;

import com.marvisx.backend.dto.BookRequest;
import com.marvisx.backend.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMapper {

    public Book toBook(BookRequest request){
        return Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .author(request.getAuthor())
                .publishedDate(request.getPublishedDate())
                .build();
    }
}
