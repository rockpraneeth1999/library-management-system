package com.example.librarymanagementsystem;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {

    @Id
    int regNo;

    String name;

    String email;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;
}
