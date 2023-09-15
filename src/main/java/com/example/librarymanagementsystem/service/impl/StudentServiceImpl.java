package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.service.StudentService;
import com.example.librarymanagementsystem.transformer.LibraryCardTransformer;
import com.example.librarymanagementsystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest) {

        //DTO StudentRequest to Model Student
        Student student = StudentTransformer.StudentRequestToStudent(studentRequest);

        //Generate LibraryCard Model
        LibraryCard libraryCard = LibraryCardTransformer.LibraryCardGenerator(student);
        student.setLibraryCard(libraryCard);

        //Save Student Model to DB
        Student savedStudent = studentRepository.save(student);

        //Model saved Student to StudentResponse
        StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(savedStudent);

        return studentResponse;
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if (!studentOptional.isPresent())
            return null;

        Student savedStudent = studentOptional.get();
        //Model to DTO
        StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(savedStudent);

        LibraryCardResponse libraryCardResponse = LibraryCardTransformer.LibraryCardResponseGenerator(savedStudent);

        studentResponse.setLibraryCardResponse(libraryCardResponse);

        return studentResponse;
    }

    public String deleteStudent(int regNo) {
        Optional<Student> studentRecord = studentRepository.findById(regNo);
        if(studentRecord.isPresent()){
            studentRepository.deleteById(regNo);
            return "Deleted Successfully";
        }
        return "Does not exists";
    }

    public StudentResponse updateStudentAge(int regNo, int age) {
        //get student record from db
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(!studentOptional.isPresent())
            return null;

        //update the age from obj
        Student savedStudent = studentOptional.get();
        savedStudent.setAge(age);
        //save the new obj to db
        studentRepository.save(savedStudent);

        //get student record from db
        studentOptional = studentRepository.findById(regNo);
        savedStudent = studentOptional.get();

        StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(savedStudent);

        studentResponse.setLibraryCardResponse(LibraryCardTransformer.LibraryCardResponseGenerator(savedStudent));

        return studentResponse;
    }

    public List<StudentResponse> getAllStudents() {
        List<Student> studentRecords = studentRepository.findAll();
        if (studentRecords.size()==0)
            return new ArrayList<>();

        //convert studentRecords to studentResponses DTO
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student: studentRecords){
            StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(student);
            studentResponse.setLibraryCardResponse(LibraryCardTransformer.LibraryCardResponseGenerator(student));

            studentResponses.add(studentResponse);
        }

        return studentResponses;
    }

    public List<StudentResponse> getMaleStudents() {
        List<Student> maleRecords = studentRepository.findByGender(Gender.MALE);
        if (maleRecords.size()==0)
            return new ArrayList<>();

        //convert studentRecords to studentResponses DTO
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student: maleRecords){
            StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(student);
            studentResponse.setLibraryCardResponse(LibraryCardTransformer.LibraryCardResponseGenerator(student));

            studentResponses.add(studentResponse);
        }

        return studentResponses;
    }
}
