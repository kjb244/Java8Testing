package com.testing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Mapper {
	
	public static void main(String[] args){
		List<Person> personList = Arrays.asList(new Person("Kevin","Bacino",35),
				new Person ("John","Francis",22),
				new Person ("Steve", "M", 45),
				new Person ("Kevin","Johnson", 23));
		
		List<String> persons = personList
								.stream()
								.map(x -> x.getFirstName())
								.collect(Collectors.toList());
		Collections.sort(persons);
		persons.forEach(x -> System.out.println(x));
		
		Map<String, Integer> personsMap = personList
										.stream()
										.collect(
										Collectors.toMap(x-> x.getFirstName(), 
														 x-> x.getAge(),
														 (address1, address2)->{
															 System.out.println("dup key");
															 return address1;
														 }
												));
		personsMap.forEach((k,v)-> System.out.println(k + " " + v));
	}

}
