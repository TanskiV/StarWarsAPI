package starwars.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import starwars.model.Film;

public interface StarWarsRepository extends MongoRepository<Film, Integer>{
Film findByPk(Integer pk);
}
