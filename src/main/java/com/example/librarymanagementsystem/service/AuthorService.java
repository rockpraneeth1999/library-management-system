package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;

public interface AuthorService {
    AuthorResponse addAuthor(AuthorRequest authorRequest);

    AuthorResponse updateEmail(int id, String email);
}
