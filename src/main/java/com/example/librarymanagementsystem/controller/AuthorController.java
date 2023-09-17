package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorServiceImpl;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest){
        try {
            AuthorResponse authorResponse = authorServiceImpl.addAuthor(authorRequest);
            return new ResponseEntity(authorResponse, HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-email")
    public ResponseEntity updateEmail(@RequestParam("id") int id,@RequestParam("email") String email){
        try {
            AuthorResponse response = authorServiceImpl.updateEmail(id,email);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
