package com.example.librarymanagementsystem.dto.requestDTO;

import com.example.librarymanagementsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorRequest {

    int id;

    String name;

    int age;

    String email;

    Gender gender;
}
