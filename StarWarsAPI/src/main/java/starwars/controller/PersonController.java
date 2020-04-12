package starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import starwars.dto.people.PersonDto;
import starwars.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping("/person")
	public boolean addPerson(@RequestBody  PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/person/{pk}")
	public PersonDto personDto(@PathVariable Integer pk) {
		return personService.findPerson(pk);
	}

	@GetMapping("/persons")
	public List<PersonDto> getAllPerson() {
		return personService.findAllPerson();
	}
	
	@PostMapping("/persons")
	public List<PersonDto> addAllPerson(@RequestBody List<PersonDto> personDtos) {
		return personService.addAllPerson(personDtos);
	}
	
	@DeleteMapping("/person/{pk}")
	public boolean deletePerson (@PathVariable Integer pk) {
		return personService.deletePerson(pk);
	}
	
	@DeleteMapping("/persons")
	public List<PersonDto> deleteAllPerson(){
		return personService.deleteAllPerson();
	}
	
	@PutMapping("/person/{pk}")
	public PersonDto updatePerson(@RequestBody PersonDto peopleDto, @PathVariable Integer pk) {
		return personService.updatePerson(peopleDto, pk);
	}

}
