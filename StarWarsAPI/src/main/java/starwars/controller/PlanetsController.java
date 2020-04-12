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

import starwars.dto.planets.PlanetsDto;
import starwars.service.PlanetsService;

@RestController
public class PlanetsController {
	@Autowired
	PlanetsService planetsService;
	
	@PostMapping("/planet")
	public boolean addPlanet(@RequestBody  PlanetsDto planetsDto) {
		return planetsService.addPlanet(planetsDto);
	}

	@GetMapping("/planet/{pk}")
	public PlanetsDto planetsDto(@PathVariable Integer pk) {
		return planetsService.findPlanet(pk);
	}

	@GetMapping("/planets")
	public List<PlanetsDto> getAllPlanets() {
		return planetsService.findAllPlanets();
	}
	
	@PostMapping("/planets")
	public List<PlanetsDto> addAllPlanets(@RequestBody List<PlanetsDto> planetsDtos) {
		return planetsService.addAllPlanets(planetsDtos);
	}
	
	@DeleteMapping("/planet/{pk}")
	public boolean deletePlanet (@PathVariable Integer pk) {
		return planetsService.deletePlanet(pk);
	}
	
	@DeleteMapping("/planets")
	public List<PlanetsDto> deleteAllPlanets(){
		return planetsService.deleteAllPlanets();
	}
	
	@PutMapping("/planet/{pk}")
	public PlanetsDto updatePlanet(@RequestBody PlanetsDto planetsDto, @PathVariable Integer pk) {
		return planetsService.updatePlanet(planetsDto, pk);
	}


}
