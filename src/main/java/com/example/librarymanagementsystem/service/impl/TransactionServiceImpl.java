package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import com.example.librarymanagementsystem.service.TransactionService;
import com.example.librarymanagementsystem.transformer.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public IssueBookResponse issueBook(int bookId, int studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent())
            throw new StudentNotFoundException("Invalid student id to issue book");

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(!bookOptional.isPresent())
            throw new BookNotAvailableException("Invalid book id to issue book");

        Book book = bookOptional.get();
        if(book.isIssued())
            throw new BookNotAvailableException("Book already issue");

        Student student = studentOptional.get();

        //create transaction
        Transaction transaction = TransactionTransformer.TransactionGenerator(book,student.getLibraryCard());
        Transaction savedTransaction = transactionRepository.save(transaction);

        //update book
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);

        //card changes
        student.getLibraryCard().getTransactions().add(savedTransaction);

        IssueBookResponse issueBookResponse = IssueBookResponse.builder()
                .id(savedTransaction.getId())
                .transactionNumber(savedTransaction.getTransactionNumber())
                .transactionTime(savedTransaction.getTransactionTime())
                .transactionStatus(savedTransaction.getTransactionStatus())
                .bookName(savedTransaction.getBook().getTitle())
                .authorName(savedTransaction.getBook().getAuthor().getName())
                .studentName(savedTransaction.getLibraryCard().getStudent().getName())
                .libraryCardNumber(savedTransaction.getLibraryCard().getCardNo())
                .build();

        return issueBookResponse;
    }
}
