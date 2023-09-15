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
        StudentResponse studentResponse = studentServiceImpl.getStudent(regNo);
        if(studentResponse==null)
            return new ResponseEntity("Invalid id",HttpStatus.BAD_REQUEST);

        return new ResponseEntity(studentResponse,HttpStatus.FOUND);
    }

    //Delete student from db
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo){
        String response = studentServiceImpl.deleteStudent(regNo);
        if(response.equals("Deleted Successfully")){
            return new ResponseEntity(response,HttpStatus.OK);
        }
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    //Update age of a student in db and return required details
    @PutMapping("/update")
    public ResponseEntity updateStudentAge(@RequestParam("id") int regNo,@RequestParam("age") int age){
        StudentResponse studentResponse = studentServiceImpl.updateStudentAge(regNo,age);
        if(studentResponse!=null)
            return new ResponseEntity(studentResponse,HttpStatus.FOUND);

        return new ResponseEntity("Invalid Id",HttpStatus.BAD_REQUEST);
    }

    //Get all student records from db
    @GetMapping("/getAll")
    public ResponseEntity getAllStudents(){
        List<StudentResponse> studentResponses = studentServiceImpl.getAllStudents();
        if(studentResponses.size()!=0)
            return new ResponseEntity(studentResponses,HttpStatus.FOUND);

        return new ResponseEntity("No records",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getMale")
    public ResponseEntity getMaleStudents(){
        List<StudentResponse> studentResponses = studentServiceImpl.getMaleStudents();
        if(studentResponses.size()!=0)
            return new ResponseEntity(studentResponses,HttpStatus.FOUND);

        return new ResponseEntity("No records",HttpStatus.BAD_REQUEST);
    }
}
