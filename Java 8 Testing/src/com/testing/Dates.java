package com.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {
	
	public static void main(String[] args){
		long seconds = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusDays(4)).getSeconds();
		
		System.out.println(seconds);
		
		String dt = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
		dt = LocalDateTime.now().format(dtf);
		System.out.println(dt);
		
		//one line
		dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy MM dd")); 
		System.out.println(dt);
	}

}
