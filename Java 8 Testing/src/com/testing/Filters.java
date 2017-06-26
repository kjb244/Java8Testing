package com.testing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Filters {
	
	public static void main(String[] args){
		List<Person> personList = Arrays.asList(new Person("Kevin","Bacino",35),
												new Person ("John","Francis",22),
												new Person ("Steve", "M", 45));
		
		Optional<Person> person = personList
								.stream()
								.filter(p -> p.getFirstName().contains("K"))
								.findAny();
		
		if (person.isPresent()){
			System.out.println(person);
		}
		
		OptionalInt personIdx =  IntStream.range(0, personList.size())
						     		.filter(i -> "Steve".equals(personList.get(i).getFirstName()))
					     			.findFirst();
		if (personIdx.isPresent()){
			System.out.println(personIdx);
		}
		
		List<Person> personListFiltered = personList
											.stream()
											.filter(p -> p.getAge() > 1)
											.collect(Collectors.toList());
		personListFiltered.forEach(i -> System.out.println(i));
	}

}
