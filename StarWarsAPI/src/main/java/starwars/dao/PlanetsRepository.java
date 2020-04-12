package starwars.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import starwars.model.planets.Planets;

public interface PlanetsRepository extends MongoRepository<Planets, Integer> {

}
