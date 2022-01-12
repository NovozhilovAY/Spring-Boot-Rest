package com.company.repositories;

import com.company.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    @Query(value = "SELECT name FROM shops WHERE district LIKE 'Сормовский' OR district LIKE 'Советский'", nativeQuery = true)
    List<String> task3B();
}
