package starwars.service;

import java.util.List;

import starwars.dto.film.FilmDto;

public interface FilmService {
	boolean addFilm(FilmDto filmDto);
	List<FilmDto> addAllFilms(List<FilmDto> allFilms);
	FilmDto findFilm (Integer pk);
	List<FilmDto> findAllFilms();

}
