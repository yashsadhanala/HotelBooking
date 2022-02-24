package net.java.hotelbooking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.java.hotelbooking.springboot.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
