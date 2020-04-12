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

import starwars.dto.film.FilmDto;
import starwars.service.FilmService;

@RestController
public class FilmController {

	@Autowired
	FilmService filmService;

	@PostMapping("/film")
	public boolean addFilm(@RequestBody  FilmDto filmDto) {
		return filmService.addFilm(filmDto);
	}

	@GetMapping("/film/{pk}")
	public FilmDto findFilm(@PathVariable Integer pk) {
		return filmService.findFilm(pk);
	}

	@GetMapping("/films")
	public List<FilmDto> getAllFilms() {
		return filmService.findAllFilms();
	}
	
	@PostMapping("/films")
	public List<FilmDto> addAllFilms(@RequestBody List<FilmDto> filmsDto) {
		return filmService.addAllFilms(filmsDto);
	}
	
	@DeleteMapping("/film/{pk}")
	public boolean deleteFilm (@PathVariable Integer pk) {
		return filmService.deleteFilm(pk);
	}
	
	@DeleteMapping("/films")
	public List<FilmDto> deleteAllFilms(){
		return filmService.deleteAllFilms();
	}
	
	@PutMapping("/film/{pk}")
	public FilmDto updateFilm(@RequestBody FilmDto filmDto, @PathVariable Integer pk) {
		return filmService.updateFilm(filmDto, pk);
	}
}
