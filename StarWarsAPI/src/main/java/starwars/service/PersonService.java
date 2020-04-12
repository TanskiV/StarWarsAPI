package starwars.service;

import java.util.List;

import starwars.dto.people.PersonDto;

public interface PersonService {
	boolean addPerson(PersonDto peopleDto);
	List<PersonDto> addAllPerson(List<PersonDto> allPeopleDtos);
	PersonDto findPerson (Integer pk);
	List<PersonDto> findAllPerson();
	boolean deletePerson(Integer pk);
	List<PersonDto> deleteAllPerson();
	PersonDto updatePerson(PersonDto peopleDto, Integer pk);
}
