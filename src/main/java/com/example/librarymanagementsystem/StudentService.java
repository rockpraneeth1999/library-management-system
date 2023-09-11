package com.example.librarymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    public Student getStudent(int regNo) {
        Optional<Student> studentRecord = studentRepository.findById(regNo);
        if(studentRecord.isPresent())
            return studentRecord.get();
        return null;
    }

    public String deleteStudent(int regNo) {
        Optional<Student> studentRecord = studentRepository.findById(regNo);
        if(studentRecord.isPresent()){
            studentRepository.deleteById(regNo);
            return "Deleted Successfully";
        }
        return "Does not exists";
    }

    public Student updateStudentAge(int regNo, int age) {
        Optional<Student> studentRecord = studentRepository.findById(regNo);
        if(studentRecord.isPresent()){
            Student student = studentRecord.get();
            student.setAge(age);
            return studentRepository.save(student);
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getMaleStudents() {
        List<Student> records = studentRepository.findAll();
        List<Student> male = new ArrayList<>();
        for(Student student : records){
            if(student.getGender() == Gender.MALE){
                male.add(student);
            }
        }
        return male;
    }
}
