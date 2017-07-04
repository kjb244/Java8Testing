package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.spring.model.Customer;

public class CustomerService {
	
	List<Customer> customers;
	
	public CustomerService(){
		customers = new ArrayList<Customer>();
		createCustomers();
	}
	
	private void createCustomers(){
		customers.add(new Customer("Kevin","Bacino", 35));
		customers.add(new Customer("Kevin","Johnson", 22));
	}
	
	public Customer addCustomer(Customer c){
		for(Customer rec: customers){
			if (rec.equals(c)){
				return null;
			}
		}
		customers.add(c);
		return c;
	}
	
	public List<Customer> getCustomers(){
		return customers;
	}

}
