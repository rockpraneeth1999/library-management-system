package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    @Override
    public BookResponse addBook(BookRequest bookRequest) {

        Optional<Author> optionalAuthor = authorRepository.findById(bookRequest.getAuthorId());
        if(!optionalAuthor.isPresent())
            throw new AuthorNotFoundException("Author not found to add this book");

        Author author = optionalAuthor.get();
        Book book = BookTransformer.BookRequestToBook(bookRequest);
        book.setAuthor(author);

        author.getBooks().add(book);
        Author savedAuthor = authorRepository.save(author);

        BookResponse bookResponse = BookTransformer.BookToBookResponse(book,author);
        return bookResponse;
    }

    @Override
    public void deleteBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent())
            throw new BookNotFoundException("Invalid Id for book deletion");

        bookRepository.delete(optionalBook.get());
    }

    @Override
    public List<BookResponse> getBookByGenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        if (bookList.size()==0)
            throw new BookNotFoundException("No books of this genre "+genre);

        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book:bookList){
            bookResponses.add(BookTransformer.BookToBookResponse(book,book.getAuthor()));
        }

        return bookResponses;
    }

    public List<BookResponse> getBookByGenreAndCost(String genre, int cost) {
        List<Book> bookList = bookRepository.getBookByGenreAndCost(genre,cost);
        if (bookList.size()==0)
            throw new BookNotFoundException("Book not found for genre and cost");

        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book book:bookList){
            bookResponseList.add(BookTransformer.BookToBookResponse(book,book.getAuthor()));
        }

        return bookResponseList;
    }

    @Override
    public List<BookResponse> getBooksOfAuthor(int authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(!authorOptional.isPresent())
            throw new AuthorNotFoundException("Invalid author id to get books of author");

        List<Book> bookList = bookRepository.getBooksOfAuthor(authorId);

        if(bookList.size()==0)
            throw new BookNotFoundException("No books written by author");

        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book book:bookList){
            bookResponseList.add(BookTransformer.BookToBookResponse(book,book.getAuthor()));
        }

        return bookResponseList;
    }
}
