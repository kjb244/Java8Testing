package com.spring.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseValidator {
	
	public boolean lengthValidator(String s, int min, int max){
		if (s == null) return false;
		return s.length() >= min && s.length() <= max;
	}
	
	public boolean regexValidator(String s, String regex){
		if (s == null) return false;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(regex);
		return m.find();
	}
	
	

}
