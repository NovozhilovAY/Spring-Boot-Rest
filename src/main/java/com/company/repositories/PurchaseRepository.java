package com.company.repositories;

import com.company.models.Purchase;
import com.company.models.task4.LastNameAndShopName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Collection;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
    @Query(value = "SELECT DISTINCT EXTRACT(MONTH from purchases.date) FROM purchases", nativeQuery = true)
    List<Double> uniqMonth();

    @Query(value = "SELECT customers.last_name, shops.name FROM customers JOIN purchases ON customers.id = purchases.customer_id JOIN shops ON shops.id = purchases.shop_id", nativeQuery = true)
    List<LastNameAndShopName> task4A();

    @Query(value = "SELECT purchases.date, customers.last_name, customers.discount, books.name, purchases.qty FROM customers JOIN purchases ON customers.id = purchases.customer_id JOIN books ON books.id = purchases.book_id", nativeQuery = true)
    Collection<Object> task4B();

    @Query(value = "SELECT p.id, c.last_name, p.date FROM purchases p JOIN customers c on c.id = p.customer_id WHERE sum > 2000", nativeQuery = true)
    Collection<Object> task5A();

    @Query(value = "SELECT c.last_name, c.district, p.date FROM purchases p JOIN customers c on c.id = p.customer_id JOIN shops s on p.shop_id = s.id WHERE c.district = s.district AND EXTRACT(MONTH FROM p.date)>=3 ORDER BY date", nativeQuery = true)
    Collection<Object> task5B();

    @Query(value = "SELECT b.name, s.district, p.qty, b.cost FROM purchases p  JOIN shops s ON p.shop_id = s.id JOIN books b on p.book_id = b.id WHERE b.qty>10 ORDER BY b.cost", nativeQuery = true)
    Collection<Object> task5D();

}
