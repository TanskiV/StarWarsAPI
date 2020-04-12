package starwars.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starwars.dao.StarWarsRepository;
import starwars.dto.film.FieldsDto;
import starwars.dto.film.FilmDto;
import starwars.model.Fields;
import starwars.model.Film;

@Service
public class FilmServiceImpl implements FilmService {
	
@Autowired
StarWarsRepository starWarsRepository;

	@Override
	public boolean addFilm(FilmDto filmDto) {
		if (!starWarsRepository.existsById(filmDto.getPk())) {
			Film film = new Film( fieldsDtoToFileds(filmDto.getFields()), filmDto.getModel(), filmDto.getPk());
			starWarsRepository.save(film);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public List<FilmDto> addAllFilms(List<FilmDto> allFilms){
				List<Film> films =	allFilms.stream()
				.map(fd -> Film.builder()
				.fields(fieldsDtoToFileds(fd.getFields()))
				.model(fd.getModel())
				.pk(fd.getPk())
				.build())
				.collect(Collectors.toList());
		
		List<Film> savedFilms = starWarsRepository.saveAll(films);
		
		return savedFilms.stream().map(sf -> FilmDto.builder().fields(fieledsToFiledsDto(sf.getFields()))
				.model(sf.getModel())
				.pk(sf.getPk())
				.build())
				.collect(Collectors.toList());
	}


	@Override
	public FilmDto findFilm(Integer pk) {
		Film film = starWarsRepository.findById(pk)
				.orElse(null);
		return FilmDto.builder().fields(fieledsToFiledsDto(film.getFields()))
				.model(film.getModel())
				.pk(film.getPk())
				.build();
	}

	@Override
	public List<FilmDto> findAllFilms() {
		List<Film> films = starWarsRepository.findAll();
		 return films.stream()
				 .map(f -> FilmDto.builder().
						 fields(fieledsToFiledsDto(f.getFields()))
						 .model(f.getModel())
						 .pk(f.getPk())
						 .build())
				 .collect(Collectors.toList());
	}

	@Override
	public boolean deleteFilm(Integer pk) {
		if (starWarsRepository.existsById(pk)) {
			 starWarsRepository.deleteById(pk);
			 return true;
		}else {
			return false;
		}
	}

	@Override
	public List<FilmDto> deleteAllFilms() {
		List<Film> allFilms = starWarsRepository.findAll();
		starWarsRepository.deleteAll();
		List<FilmDto> allFilmsDtos = allFilms.stream()
				.map(f -> new FilmDto(fieledsToFiledsDto(f.getFields()), f.getModel(), f.getPk()))
				.collect(Collectors.toList());
		return allFilmsDtos;
	}
	
	@Override
	public FilmDto updateFilm(FilmDto filmDto, Integer pk) {
	Film film = starWarsRepository.findById(pk).orElseThrow();
		
	FilmDto returnDto =	FilmDto.builder()
		.fields(fieledsToFiledsDto(film.getFields()))
		.pk(film.getPk())
		.model(film.getModel())
		.build();
	
	starWarsRepository.delete(film);
		
	Film updateFilm = film;
				updateFilm.setFields(fieldsDtoToFileds(filmDto.getFields()));
				updateFilm.setPk(filmDto.getPk());
				updateFilm.setModel(filmDto.getModel());	
				
	starWarsRepository.save(updateFilm);
	
	return returnDto;
		
	}
	
	private Fields fieldsDtoToFileds(FieldsDto fieldsDto) {
		return Fields.builder()
				.starships(fieldsDto.getStarships())
				.edited(fieldsDto.getEdited())
				.vehicles(fieldsDto.getVehicles())
				.planets(fieldsDto.getPlanets())
				.producer(fieldsDto.getProducer())
				.title(fieldsDto.getTitle())
				.created(fieldsDto.getCreated())
				.episode_id(fieldsDto.getEpisode_id())
				.director(fieldsDto.getDirector())
				.release_date(fieldsDto.getRelease_date())
				.opening_crawl(fieldsDto.getOpening_crawl())
				.characters(fieldsDto.getCharacters())
				.species(fieldsDto.getSpecies())
				.build();

	}
	private FieldsDto fieledsToFiledsDto(Fields fields) {
		return FieldsDto.builder().characters(fields.getCharacters())
				.created(fields.getCreated())
				.director(fields.getDirector())
				.edited(fields.getEdited())
				.episode_id(fields.getEpisode_id())
				.opening_crawl(fields.getOpening_crawl())
				.planets(fields.getPlanets())
				.producer(fields.getProducer())
				.release_date(fields.getRelease_date())
				.species(fields.getSpecies())
				.starships(fields.getStarships())
				.title(fields.getTitle())
				.vehicles(fields.getVehicles())
				.build();
	}

}
