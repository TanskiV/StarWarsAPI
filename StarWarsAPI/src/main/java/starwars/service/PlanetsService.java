package starwars.service;

import java.util.List;

import starwars.dto.planets.PlanetsDto;

public interface PlanetsService {
	boolean addPlanet(PlanetsDto planetsDto);
	List<PlanetsDto> addAllPlanets(List<PlanetsDto> allPlanetsDtos);
	PlanetsDto findPlanet (Integer pk);
	List<PlanetsDto> findAllPlanets();
	boolean deletePlanet(Integer pk);
	List<PlanetsDto> deleteAllPlanets();
	PlanetsDto updatePlanet(PlanetsDto planetsDto, Integer pk);
}
