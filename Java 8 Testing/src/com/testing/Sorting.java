package com.testing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorting {

	public static void main(String[] args) {
		List<Person> personList = Arrays.asList(new Person("Kevin","Bacino",35),
				new Person ("John","Francis",22),
				new Person ("Steve", "M", 45));
		
		personList.sort(Comparator.comparing((Person p) -> -1*p.getAge())
				  .thenComparing(p -> p.getLastName()));
		
		personList.forEach(p -> System.out.println(p));
		
		

	}

}
