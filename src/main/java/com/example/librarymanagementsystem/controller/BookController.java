package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody BookRequest bookRequest){
        try {
            BookResponse response = bookServiceImpl.addBook(bookRequest);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam("id") int id){
        try {
            bookServiceImpl.deleteBook(id);
            return new ResponseEntity("Book Deleted",HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("book-by-genre")
    public ResponseEntity getBookByGenre(@RequestParam("genre") Genre genre){
        try {
            List<BookResponse> response = bookServiceImpl.getBookByGenre(genre);
            return new ResponseEntity(response,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("book-by-genre-cost")
    public ResponseEntity getBookByGenreAndCost(@RequestParam("genre") String genre,@RequestParam("cost") int cost){
        try {
            List<BookResponse> response = bookServiceImpl.getBookByGenreAndCost(genre,cost);
            return new ResponseEntity(response,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-book-of-author")
    public ResponseEntity getBooksOfAuthor(@RequestParam("id") int authorId){
        try {
            List<BookResponse> response = bookServiceImpl.getBooksOfAuthor(authorId);
            return new ResponseEntity(response,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
