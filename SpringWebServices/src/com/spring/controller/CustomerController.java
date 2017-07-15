package com.spring.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





















import com.spring.model.Customer;
import com.spring.service.CustomerService;
import com.spring.validators.NameValidators;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private NameValidators nameValidators;
	
	@RequestMapping(value="/customers", method = RequestMethod.GET)
	public ResponseEntity getCustomer(){
		return new ResponseEntity(customerService.getCustomers(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/createCustomer", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity createCustomer(@Valid @RequestBody Customer customer, Errors errors){

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

			// get all errors
        	String errString = errors.getAllErrors()
        									.stream()
        									.map(x -> x.getDefaultMessage())
        									.collect(Collectors.joining(","));
        	
        	Map<String,String> hm = new HashMap<String, String>();
        	hm.put("errors", errString);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            
        }
		return new ResponseEntity(customerService.addCustomer(customer), HttpStatus.OK);
	}
	
	@RequestMapping(value="/createCustomerCustomValidation", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity createCustomerCustomValidation(@RequestBody String s) throws ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
		System.out.println(s);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		Set<String> jsonKeys = json.keySet();
		if (jsonKeys.contains("firstName")){
			Boolean validated = nameValidators.firstName((String) json.get("firstName"));
			System.out.println(validated);
		}
		Boolean status = validateKeysViaReflection(json);
		if (status) return new ResponseEntity("good", HttpStatus.OK);
		
		return new ResponseEntity("bad", HttpStatus.OK);
		
		
	}
	
	private Boolean validateKeysViaReflection(JSONObject json) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
		Class clazz = NameValidators.class;
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		Boolean rtnBool = true;
		List<String> methodsAvailable = Arrays.asList(clazz.getMethods())
												.stream()
												.map(x -> x.getName())
												.collect(Collectors.toList());
		
		for(Iterator iterator = json.keySet().iterator(); iterator.hasNext();) {
		    String key = (String) iterator.next();
		    if (methodsAvailable.contains(key)){
				Object obj = clazz.newInstance();
				Method meth = clazz.getDeclaredMethod(key, paramString);
				
				Boolean methodStatus = (Boolean) meth.invoke(obj,json.get(key));
				if (!methodStatus) return false;
		    	
		    }
		    
		}
		
		
		/*for (Method method : clazz.getMethods()){
			String value = (String) json.get(method.getName());
			if (value == null) continue;
			
			Object obj = clazz.newInstance();
			Method meth = clazz.getDeclaredMethod(method.getName(), paramString);
			
			Boolean methodStatus = (Boolean) meth.invoke(obj,value);
			if (!methodStatus) return false;
         
		}*/
		
		return true;
	}
}
