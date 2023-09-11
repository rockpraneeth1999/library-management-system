package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public String addStudent(Student student) {
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(UUID.randomUUID().toString());
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);

        Student savedStudent = studentRepository.save(student);
        return "Saved";
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
