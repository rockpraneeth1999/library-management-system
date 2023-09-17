package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookTransformer {

    public static Book BookRequestToBook(BookRequest bookRequest){
        return Book.builder()
                .title(bookRequest.getTitle())
                .genre(bookRequest.getGenre())
                .cost(bookRequest.getCost())
                .noOfPages(bookRequest.getNoOfPages())
                .issued(false)
                .build();
    }

    public static BookResponse BookToBookResponse(Book book, Author author){
        return BookResponse.builder()
                .title(book.getTitle())
                .noOfPages(book.getNoOfPages())
                .cost(book.getCost())
                .genre(book.getGenre())
                .authorName(author.getName())
                .build();
    }
}
