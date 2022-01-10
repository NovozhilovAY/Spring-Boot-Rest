package com.company.repositories;

import com.company.models.Customer;
import com.company.models.task3.LastNameAndDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT DISTINCT district FROM customers", nativeQuery = true)
    List<String> getUniqDistricts();

    List<LastNameAndDiscounts> findCustomersByDistrict(String district);

}
