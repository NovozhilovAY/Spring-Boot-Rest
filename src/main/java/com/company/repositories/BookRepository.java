package com.company.repositories;

import com.company.models.Book;
import com.company.models.task3.NamesAndCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT DISTINCT name FROM books", nativeQuery = true)
    List<String> getUniqNames();

    @Query(value = "SELECT DISTINCT cost FROM books", nativeQuery = true)
    List<Double> getUniqCosts();

    @Query(value = "SELECT DISTINCT name, cost FROM books WHERE cost > 20000 OR name LIKE '%Windows%'", nativeQuery = true)
    List<NamesAndCosts> task3C();
}
