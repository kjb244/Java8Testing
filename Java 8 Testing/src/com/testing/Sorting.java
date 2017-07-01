package com.testing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorting {

	public static void main(String[] args) {
		List<Person> personList = Arrays.asList(new Person("Kevin","Bacino",35),
				new Person ("John","Francis",22),
				new Person ("Steve", "M", 45));
		
		personList.sort(Comparator.comparing((Person p) -> -1*p.getAge())
				  .thenComparing(p -> p.getLastName()));
		
		personList.forEach(p -> System.out.println(p));
		
		List<Person> personList2 = personList.stream()
				                             .filter(x -> x.getAge() > 22)
				                             .sorted(Comparator.comparing(p -> ((Person) p).getLastName()).reversed())
				                             .collect(Collectors.toList());
		System.out.println();
		personList2.forEach(x -> System.out.println(x));
		
		Person pMinAge = personList.stream()
								   .min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).get();
		
		System.out.println();
		System.out.println(pMinAge);
		
		Person person = personList.stream()
								  .filter(x -> x.getAge() > 22)
								  .sorted(Comparator.comparing(x -> -1*x.getAge()))
								  .findFirst()
								  .orElse(null);
		
		System.out.println();
		System.out.println(person);
		
		

	}

}
