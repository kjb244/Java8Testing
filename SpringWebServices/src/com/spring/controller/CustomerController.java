package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Customer;
import com.spring.service.CustomerService;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/customers", method = RequestMethod.GET)
	public ResponseEntity getCustomer(){
		return new ResponseEntity(customerService.getCustomers(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/createCustomer", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity createCustomer(@RequestBody Customer customer){
		return new ResponseEntity(customerService.addCustomer(customer), HttpStatus.OK);
	}

}
