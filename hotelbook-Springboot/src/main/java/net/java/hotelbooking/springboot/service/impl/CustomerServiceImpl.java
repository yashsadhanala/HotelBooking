package net.java.hotelbooking.springboot.service.impl;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
import net.java.hotelbooking.springboot.exception.ResourceNotFoundException;
//import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
import net.java.hotelbooking.springboot.model.Customer;
import net.java.hotelbooking.springboot.repository.CustomerRepository;
import net.java.hotelbooking.springboot.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	public CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	@Override
	public Customer saveCustomer(Customer customer) {
//		System.out.println("hi2");
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(long customer_id) {
		return customerRepository.findById(customer_id).orElseThrow(()->( new ResourceNotFoundException("Customer","Customer_id",customer_id)));
		
		
	}

	@Override
	public Customer updateCustomer(Customer customer, long customer_id) {
		Customer existingCustomer = customerRepository.findById(customer_id).orElseThrow(()-> new ResourceNotFoundException("Customer","Customer_id",customer_id));
		existingCustomer.setCustomername(customer.getCustomername());
		existingCustomer.setContact(customer.getContact());
		existingCustomer.setEmail(customer.getEmail());
		customerRepository.save(existingCustomer);
		return existingCustomer;
	}

	
	@Override
	public void deleteCustomer(long customer_id) {
		customerRepository.findById(customer_id).orElseThrow(()->
		new ResourceNotFoundException("Customer","Customer_id",customer_id));
        customerRepository.deleteById(customer_id);
		
	}
	
}

