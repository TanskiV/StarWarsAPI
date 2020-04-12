package starwars.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import starwars.model.films.Film;

public interface FilmsRepository extends MongoRepository<Film, Integer>{

	
}
