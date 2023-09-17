package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.Student;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentTransformer {

    public static Student StudentRequestToStudent(StudentRequest studentRequest){

        return Student.builder()
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .name(studentRequest.getName())
                .name(studentRequest.getName())
                .gender(studentRequest.getGender())
                .build();
    }

    public static StudentResponse StudentToStudentResponse(Student student){

        StudentResponse studentResponse = StudentResponse.builder()
                .regNo(student.getRegNo())
                .email(student.getEmail())
                .name(student.getName())
                .age(student.getAge())
                .gender(student.getGender())
                .build();

        studentResponse.setLibraryCardResponse(LibraryCardTransformer.LibraryCardResponseGenerator(student));

        return studentResponse;
    }
}
