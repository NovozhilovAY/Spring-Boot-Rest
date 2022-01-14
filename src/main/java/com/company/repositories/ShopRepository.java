package com.company.repositories;

import com.company.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    @Query(value = "SELECT name FROM shops WHERE district LIKE 'Сормовский' OR district LIKE 'Советский'", nativeQuery = true)
    List<String> task3B();

    @Query(value = "SELECT s.id, s.name, s.district, s.commission FROM purchases p JOIN customers c on c.id = p.customer_id JOIN shops s on p.shop_id = s.id WHERE s.district NOT LIKE 'Автозаводский' AND c.discount BETWEEN 10 AND 15",nativeQuery = true)
    List<Shop> task5C();
}
