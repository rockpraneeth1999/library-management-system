package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse addBook(BookRequest bookRequest);

    void deleteBook(int id);

    List<BookResponse> getBookByGenre(Genre genre);

    public List<BookResponse> getBookByGenreAndCost(String genre, int cost);

    List<BookResponse> getBooksOfAuthor(int authorId);
}
