package net.java.hotelbooking.springboot.controller;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.java.hotelbooking.springboot.model.Customer;
import net.java.hotelbooking.springboot.service.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private CustomerService customerService;

	public CustomerController(net.java.hotelbooking.springboot.service.CustomerService customerService) {
        super();
		this.customerService = customerService;
  	}
	@PostMapping()
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){

		return new ResponseEntity<Customer>(customerService.saveCustomer(customer),HttpStatus.CREATED);
		
}
	@GetMapping()
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();	}

	
	@GetMapping("{customer_id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customer_id") long customer_id){
		return new ResponseEntity<Customer>(customerService.getCustomerById(customer_id),HttpStatus.OK);
	}
	
	
	@PutMapping("{customer_id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("customer_id") long customer_id, @RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.updateCustomer(customer, customer_id),HttpStatus.OK);
	}


	
	@DeleteMapping("{customer_id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customer_id")long customer_id){
			customerService.deleteCustomer(customer_id);
			return new ResponseEntity<String>("Customer Deleted succesfully!.",HttpStatus.OK);
	}
	

}






//@RequestParam(name = "Customername", required = false, defaultValue = "Vindhya") String name,
//@RequestParam(name = "Email", required = false, defaultValue = "Rai") String Email,
//@RequestParam(name= "Contact",required = false , defaultValue = "13548262" ) long contact


//	System.out.println(customer.getEmail());
//	Customer customer = new Customer();
//	customer.setContact(contact);
//	customer.setEmail(Email);
//	customer.setCustomername(name);
