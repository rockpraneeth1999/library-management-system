package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.service.AuthorService;
import com.example.librarymanagementsystem.transformer.AuthorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    @Override
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author = AuthorTransformer.AuthorRequestToAuthor(authorRequest);
        Author savedAuthor =  authorRepository.save(author);

        AuthorResponse authorResponse = AuthorTransformer.AuthorToAuthorResponse(savedAuthor);
        return authorResponse;
    }

    @Override
    public AuthorResponse updateEmail(int id, String email) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(!optionalAuthor.isPresent())
            throw new AuthorNotFoundException("Invalid Id for update email");

        Author author = optionalAuthor.get();
        author.setEmail(email);

        Author savedAuthor = authorRepository.save(author);
        return AuthorTransformer.AuthorToAuthorResponse(savedAuthor);
    }
}
