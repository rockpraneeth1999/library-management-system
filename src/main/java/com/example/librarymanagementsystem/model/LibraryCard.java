package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String cardNo;

    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @CreationTimestamp
    Date issueDate;

    @OneToOne
    @JoinColumn
    Student student;
}
