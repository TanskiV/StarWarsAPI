package starwars.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starwars.dao.PlanetsRepository;
import starwars.dto.planets.PlanetsDto;
import starwars.dto.planets.PlanetsFieldDto;
import starwars.model.planets.Planets;
import starwars.model.planets.PlanetsField;
@Service
public class PlanetsServiceImpl implements PlanetsService {
	
@Autowired
PlanetsRepository planetsRepository;

	@Override
	public boolean addPlanet(PlanetsDto planetsDto) {
		if (!planetsRepository.existsById(planetsDto.getPk())) {
			Planets planets = Planets.builder()
					.fields(fieldsDtoToFields(planetsDto.getFields()))
					.pk(planetsDto.getPk())
					.model(planetsDto.getModel())
					.build();
			planetsRepository.save(planets);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public List<PlanetsDto> addAllPlanets(List<PlanetsDto> allPlanetsDtos) {
		List<Planets> planets = allPlanetsDtos.stream().map(pd -> Planets.builder()
				.fields(fieldsDtoToFields(pd.getFields()))
				.pk(pd.getPk())
				.model(pd.getModel())
				.build())
				.collect(Collectors.toList());
		
		List<Planets> savedPlanets = planetsRepository.saveAll(planets);
		
		return savedPlanets.stream()
				.map(sp -> PlanetsDto.builder().fields(fieledsToFiledsDto(sp.getFields()))
				.model(sp.getModel())
				.pk(sp.getPk())
				.build())
				.collect(Collectors.toList());
	}


	@Override
	public PlanetsDto findPlanet(Integer pk) {
		Planets planets = planetsRepository.findById(pk)
				.orElse(new Planets());
		return PlanetsDto.builder().fields(fieledsToFiledsDto(planets.getFields()))
				.model(planets.getModel())
				.pk(planets.getPk())
				.build();
	}

	@Override
	public List<PlanetsDto> findAllPlanets() {
		List<Planets> planet = planetsRepository.findAll();
		 return planet.stream()
				 .map(p -> PlanetsDto.builder().
						 fields(fieledsToFiledsDto(p.getFields()))
						 .model(p.getModel())
						 .pk(p.getPk())
						 .build())
				 .collect(Collectors.toList());
	}

	@Override
	public boolean deletePlanet(Integer pk) {
		if (planetsRepository.existsById(pk)) {
			 planetsRepository.deleteById(pk);
			 return true;
		}else {
			return false;
		}
	}

	@Override
	public List<PlanetsDto> deleteAllPlanets() {
		List<Planets> allPlanets = planetsRepository.findAll();
		planetsRepository.deleteAll();
		return allPlanets.stream()
				.map(p -> PlanetsDto.builder()
						.fields(fieledsToFiledsDto(p.getFields()))
						.pk(p.getPk())
						.model(p.getModel())
						.build())
				.collect(Collectors.toList());
				
	}

	@Override
	public PlanetsDto updatePlanet(PlanetsDto planetsDto, Integer pk) {
		Planets planets = planetsRepository.findById(pk).orElse(new Planets());
		PlanetsDto returnDto =	PlanetsDto.builder()
			.fields(fieledsToFiledsDto(planets.getFields()))
			.pk(planets.getPk())
			.model(planets.getModel())
			.build();
		
		planetsRepository.delete(planets);
			
		Planets updatePeople = planets;
					updatePeople.setFields(fieldsDtoToFields(planetsDto.getFields()));
					updatePeople.setPk(planetsDto.getPk());
					updatePeople.setModel(planetsDto.getModel());	
					
		planetsRepository.save(updatePeople);
		
		return returnDto;
	}
	
	private PlanetsField fieldsDtoToFields(PlanetsFieldDto fields) {
		return PlanetsField.builder()
				.edited(fields.getEdited())
				.climate(fields.getClimate())
				.surface_water(fields.getSurface_water())
				.name(fields.getName())
				.diameter(fields.getDiameter())
				.rotation_period(fields.getRotation_period())
				.created(fields.getCreated())
				.terrain(fields.getTerrain())
				.gravity(fields.getGravity())
				.orbital_period(fields.getOrbital_period())
				.population(fields.getPopulation())
				.build();
	}
	
	private PlanetsFieldDto fieledsToFiledsDto(PlanetsField fields) {
		return PlanetsFieldDto.builder()
				.edited(fields.getEdited())
				.climate(fields.getClimate())
				.surface_water(fields.getSurface_water())
				.name(fields.getName())
				.diameter(fields.getDiameter())
				.rotation_period(fields.getRotation_period())
				.created(fields.getCreated())
				.terrain(fields.getTerrain())
				.gravity(fields.getGravity())
				.orbital_period(fields.getOrbital_period())
				.population(fields.getPopulation())
				.build();
	}

}
