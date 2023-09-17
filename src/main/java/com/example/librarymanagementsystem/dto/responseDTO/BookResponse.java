package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookResponse {

    String title;

    int noOfPages;

    Genre genre;

    double cost;

    String authorName;
}
