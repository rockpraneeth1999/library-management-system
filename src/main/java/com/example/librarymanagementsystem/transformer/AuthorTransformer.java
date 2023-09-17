package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.model.Author;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorTransformer {

    public static Author AuthorRequestToAuthor(AuthorRequest authorRequest){

        return Author.builder()
                .name(authorRequest.getName())
                .age(authorRequest.getAge())
                .email(authorRequest.getEmail())
                .gender(authorRequest.getGender())
                .build();
    }

    public static AuthorResponse AuthorToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .name(author.getName())
                .id(author.getId())
                .age(author.getAge())
                .gender(author.getGender())
                .email(author.getEmail())
                .build();
    }
}
