package starwars.configuration;

import org.springframework.context.annotation.Configuration;

import starwars.service.FilmService;
import starwars.service.FilmServiceImpl;

@Configuration
public class FilmConfiguration {
	public FilmService getFilmService(){
		return new FilmServiceImpl();
	}

}
