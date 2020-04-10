package starwars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starwars.dao.StarWarsRepository;
import starwars.dto.film.FilmDto;
import starwars.model.Film;

@Service
public class FilmServiceImpl implements FilmService {
	
@Autowired
StarWarsRepository starWarsRepository;

	@Override
	public boolean addFilm(FilmDto filmDto) {
		if (!starWarsRepository.existsById(filmDto.getPk())) {
			Film film = new Film(filmDto.getFields(), filmDto.getModel(), filmDto.getPk());
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
		return FilmDto.builder().fields(film.getFields())
				.model(film.getModel())
				.pk(film.getPk())
				.build();
	}

}
