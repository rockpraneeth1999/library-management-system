package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorResponse {
    int id;

    String name;

    int age;

    String email;

    Gender gender;
}
