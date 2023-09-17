package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Transaction;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TransactionTransformer {

    public static Transaction TransactionGenerator(Book book, LibraryCard libraryCard){

        return Transaction.builder()
                .transactionNumber(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(libraryCard)
                .build();
    }
}
