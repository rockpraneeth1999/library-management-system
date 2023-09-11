package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.service.StudentService;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        String response = studentService.addStudent(student);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        Student response = studentService.getStudent(regNo);
        if(response!=null)
            return new ResponseEntity(response,HttpStatus.FOUND);

        return new ResponseEntity("Invalid Id",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo){
        String response = studentService.deleteStudent(regNo);
        if(response.equals("Deleted Successfully")){
            return new ResponseEntity(response,HttpStatus.OK);
        }
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity updateStudentAge(@RequestParam("id") int regNo,@RequestParam("age") int age){
        Student response = studentService.updateStudentAge(regNo,age);
        if(response!=null)
            return new ResponseEntity(response,HttpStatus.FOUND);

        return new ResponseEntity("Invalid Id",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllStudents(){
        List<Student> response = studentService.getAllStudents();
        if(response.size()!=0)
            return new ResponseEntity(response,HttpStatus.FOUND);

        return new ResponseEntity("No records",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getMale")
    public ResponseEntity getMaleStudents(){
        List<Student> response = studentService.getMaleStudents();
        if(response.size()!=0)
            return new ResponseEntity(response,HttpStatus.FOUND);

        return new ResponseEntity("No records",HttpStatus.BAD_REQUEST);
    }
}
