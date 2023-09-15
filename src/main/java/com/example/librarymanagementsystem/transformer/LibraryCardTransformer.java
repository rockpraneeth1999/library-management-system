package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;

import java.util.UUID;

public class LibraryCardTransformer {
    public static LibraryCard LibraryCardGenerator(Student student){

        return LibraryCard.builder()
                .cardNo(UUID.randomUUID().toString())
                .cardStatus(CardStatus.ACTIVE)
                .student(student).build();
    }

    public static LibraryCardResponse LibraryCardResponseGenerator(Student student){

        return LibraryCardResponse.builder()
                .cardNo(student.getLibraryCard().getCardNo())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .issueDate(student.getLibraryCard().getIssueDate())
                .build();

    }
}
