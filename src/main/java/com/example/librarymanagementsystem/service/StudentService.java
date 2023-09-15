package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;

import java.util.List;

public interface StudentService{

    public StudentResponse addStudent(StudentRequest studentRequest);
    public StudentResponse getStudent(int regNo);
    public void deleteStudent(int regNo);
    public StudentResponse updateStudentAge(int regNo, int age);
    public List<StudentResponse> getAllStudents();
    public List<StudentResponse> getMaleStudents();
}
