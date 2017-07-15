package com.spring.validators;

public class NameValidators extends BaseValidator{

	public boolean firstName(String s){
		return this.lengthValidator(s, 5, 100) && this.regexValidator(s, "[A-z]+");
	}
	
}
