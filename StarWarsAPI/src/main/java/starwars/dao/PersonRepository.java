package starwars.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import starwars.model.people.Person;

public interface PersonRepository extends MongoRepository<Person, Integer> {

}
