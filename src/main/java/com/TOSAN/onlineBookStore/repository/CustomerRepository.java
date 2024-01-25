package com.TOSAN.onlineBookStore.repository;

import com.TOSAN.onlineBookStore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}