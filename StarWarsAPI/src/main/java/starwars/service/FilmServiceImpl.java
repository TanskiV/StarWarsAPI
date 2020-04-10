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
			Film film = new Film(fieldsDtoToFileds(filmDto.getFieldsDto()), filmDto.getModel(), filmDto.getPk());
			starWarsRepository.save(film);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public FilmDto findFilm(Integer pk) {
		Film film = starWarsRepository.findById(pk)
				.orElse(null);
		return FilmDto.builder().fieldsDto(filedsToFiledsDto(film.getFields()))
				.model(film.getModel())
				.pk(film.getPk())
				.build();
	}

	@Override
	public List<FilmDto> findAllFilms() {
		List<Film> films = starWarsRepository.findAll();
		 return films.stream()
				 .map(f -> FilmDto.builder().
						 fieldsDto(filedsToFiledsDto(f.getFields()))
						 .model(f.getModel())
						 .pk(f.getPk())
						 .build())
				 .collect(Collectors.toList());
	}

	@Override
	public List<FilmDto> addAllFilms(List<FilmDto> allFilms){
				List<Film> films =	allFilms.stream()
				.map(fd -> Film.builder()
				.fields(fieldsDtoToFileds(fd.getFieldsDto()))
				.model(fd.getModel())
				.pk(fd.getPk())
				.build())
				.collect(Collectors.toList());
		
		List<Film> savedFilms = starWarsRepository.saveAll(films);
		
		return savedFilms.stream().map(sf -> FilmDto.builder().fieldsDto(filedsToFiledsDto(sf.getFields()))
				.model(sf.getModel())
				.pk(sf.getPk())
				.build())
				.collect(Collectors.toList());
	}
	
	private Fields fieldsDtoToFileds(FieldsDto fieldsDto) {
//		Fields fields =  Fields.builder()
//				.starships(fieldsDto.getStarships())
//				.edited(fieldsDto.getEdited())
//				.vehicles(fieldsDto.getVehicles())
//				.planets(fieldsDto.getPlanets())
//				.producer(fieldsDto.getProducer())
//				.title(fieldsDto.getTitle())
//				.created(fieldsDto.getCreated())
//				.episode_id(fieldsDto.getEpisode_id())
//				.director(fieldsDto.getDirector())
//				.release_date(fieldsDto.getRelease_date())
//				.opening_crawl(fieldsDto.getOpening_crawl())
//				.characters(fieldsDto.getCharacters())
//				.species(fieldsDto.getSpecies())
//				.build();
		Fields fields = new Fields(fieldsDto.getStarships(),
				fieldsDto.getEdited(), fieldsDto.getVehicles(),
				fieldsDto.getPlanets(), fieldsDto.getProducer(), 
				fieldsDto.getTitle(), fieldsDto.getCreated(),
				fieldsDto.getEpisode_id(), fieldsDto.getDirector(),
				fieldsDto.getRelease_date(), fieldsDto.getOpening_crawl(),
				fieldsDto.getCharacters(), fieldsDto.getSpecies());
		return fields;
	}
	private FieldsDto filedsToFiledsDto(Fields fields) {
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
