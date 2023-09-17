package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    public List<Book> findByGenre(Genre genre);

    @Query(value = "select * from book where genre = :genre and cost > :cost",nativeQuery = true)
    List<Book> getBookByGenreAndCost(String genre,int cost);

    @Query(value = "select b from Book b where b.author.id = :authorId")
    List<Book> getBooksOfAuthor(int authorId);
}
