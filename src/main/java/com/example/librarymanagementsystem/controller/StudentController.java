package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentServiceImpl;

    //Add student to db and return Required details
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse studentResponse = studentServiceImpl.addStudent(studentRequest);
        return new ResponseEntity(studentResponse, HttpStatus.CREATED);
    }

    //Get student from db
    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        try {
            StudentResponse studentResponse = studentServiceImpl.getStudent(regNo);
            return new ResponseEntity(studentResponse,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    //Delete student from db
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo){
        try {
            studentServiceImpl.deleteStudent(regNo);
            return new ResponseEntity("Deleted successfully",HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    //Update age of a student in db and return required details
    @PutMapping("/update")
    public ResponseEntity updateStudentAge(@RequestParam("id") int regNo,@RequestParam("age") int age){
        try {
            StudentResponse studentResponse = studentServiceImpl.updateStudentAge(regNo,age);
            return new ResponseEntity(studentResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //Get all student records from db
    @GetMapping("/getAll")
    public ResponseEntity getAllStudents(){
        try {
            List<StudentResponse> studentResponses = studentServiceImpl.getAllStudents();
            return new ResponseEntity(studentResponses,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMale")
    public ResponseEntity getMaleStudents(){
        try {
            List<StudentResponse> studentResponses = studentServiceImpl.getMaleStudents();
            return new ResponseEntity("No records",HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.FOUND);
        }
    }
}
