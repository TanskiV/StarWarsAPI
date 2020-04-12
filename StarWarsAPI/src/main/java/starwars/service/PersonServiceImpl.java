package starwars.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starwars.dao.PersonRepository;
import starwars.dto.people.PersonDto;
import starwars.dto.people.PersonFieldsDto;
import starwars.model.people.Person;
import starwars.model.people.PersonFields;
@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
PersonRepository personRepository;
	
	@Override
	public boolean addPerson(PersonDto personDto) {
		if (!personRepository.existsById(personDto.getPk())) {
			Person person = Person.builder()
					.fields(fieldsDtoToFields(personDto.getFields()))
					.pk(personDto.getPk())
					.model(personDto.getModel())
					.build();
			personRepository.save(person);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public List<PersonDto> addAllPerson(List<PersonDto> allPersonDtos) {
		List<Person> peoples = allPersonDtos.stream().map(pd -> Person.builder()
				.fields(fieldsDtoToFields(pd.getFields()))
				.pk(pd.getPk())
				.model(pd.getModel())
				.build())
				.collect(Collectors.toList());
		
		List<Person> savedPerson = personRepository.saveAll(peoples);
		
		return savedPerson.stream()
				.map(sp -> PersonDto.builder().fields(fieledsToFiledsDto(sp.getFields()))
				.model(sp.getModel())
				.pk(sp.getPk())
				.build())
				.collect(Collectors.toList());
	}

	@Override
	public PersonDto findPerson(Integer pk) {
		Person person = personRepository.findById(pk)
				.orElse(new Person());
		return PersonDto.builder().fields(fieledsToFiledsDto(person.getFields()))
				.model(person.getModel())
				.pk(person.getPk())
				.build();
	}

	@Override
	public List<PersonDto> findAllPerson() {
		List<Person> person = personRepository.findAll();
		 return person.stream()
				 .map(p -> PersonDto.builder().
						 fields(fieledsToFiledsDto(p.getFields()))
						 .model(p.getModel())
						 .pk(p.getPk())
						 .build())
				 .collect(Collectors.toList());
	}

	@Override
	public boolean deletePerson(Integer pk) {
		if (personRepository.existsById(pk)) {
			 personRepository.deleteById(pk);
			 return true;
		}else {
			return false;
		}
	}

	@Override
	public List<PersonDto> deleteAllPerson() {
		List<Person> allPerson = personRepository.findAll();
		personRepository.deleteAll();
		List<PersonDto> allPersonDtos = allPerson.stream()
				.map(p -> PersonDto.builder()
						.fields(fieledsToFiledsDto(p.getFields()))
						.pk(p.getPk())
						.model(p.getModel())
						.build())
				.collect(Collectors.toList());
				
		return allPersonDtos;
	}

	@Override
	public PersonDto updatePerson(PersonDto personDto, Integer pk) {
		Person person = personRepository.findById(pk).orElse(new Person());
		PersonDto returnDto =	PersonDto.builder()
			.fields(fieledsToFiledsDto(person.getFields()))
			.pk(person.getPk())
			.model(person.getModel())
			.build();
		
		personRepository.delete(person);
			
		Person updatePeople = person;
					updatePeople.setFields(fieldsDtoToFields(personDto.getFields()));
					updatePeople.setPk(personDto.getPk());
					updatePeople.setModel(personDto.getModel());	
					
		personRepository.save(updatePeople);
		
		return returnDto;
	}
	
	private PersonFields fieldsDtoToFields(PersonFieldsDto fields) {
		return PersonFields.builder()
				.edited(fields.getEdited())
				.name(fields.getName())
				.created(fields.getCreated())
				.gender(fields.getGender())
				.skin_color(fields.getSkin_color())
				.hair_color(fields.getHair_color())
				.height(fields.getHeight())
				.eye_color(fields.getEye_color())
				.mass(fields.getMass())
				.homeworld(fields.getHomeworld())
				.birth_year(fields.getBirth_year())
				.image(fields.getImage())
				.build();		
	}
	
	private PersonFieldsDto fieledsToFiledsDto(PersonFields fields) {
		return PersonFieldsDto.builder()
				.edited(fields.getEdited())
				.name(fields.getName())
				.created(fields.getCreated())
				.gender(fields.getGender())
				.skin_color(fields.getSkin_color())
				.hair_color(fields.getHair_color())
				.height(fields.getHeight())
				.eye_color(fields.getEye_color())
				.mass(fields.getMass())
				.homeworld(fields.getHomeworld())
				.birth_year(fields.getBirth_year())
				.image(fields.getImage())
				.build();		
	}
	

}
