package com.example.librarymanagementsystem.dto.requestDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
    String title;

    int noOfPages;

    Genre genre;

    double cost;

    int authorId;
}
