package net.java.hotelbooking.springboot.service;


import java.util.List;
import net.java.hotelbooking.springboot.model.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer customer);
	List <Customer>getAllCustomers();
	Customer getCustomerById(long customer_id);
	Customer updateCustomer(Customer customer,long customer_id);
	void deleteCustomer(long customer_id);
	}
