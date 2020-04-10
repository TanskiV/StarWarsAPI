package starwars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import starwars.dto.film.FilmDto;
import starwars.service.FilmService;

@RestController
public class FilmController {

	@Autowired
	FilmService filmService;

	@PostMapping("/film")
	public boolean addFilm(@RequestBody FilmDto filmDto) {
		return filmService.addFilm(filmDto);
	}

	@GetMapping("/film/{pk}")
	public FilmDto findFilm(@PathVariable Integer pk) {
		return filmService.findFilm(pk);
	}

}
