package starwars.service;

import starwars.dto.film.FilmDto;

public interface FilmService {
	boolean addFilm(FilmDto filmDto);
	FilmDto findFilm (Integer pk);

}
