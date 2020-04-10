package starwars.dto.film;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FilmDto {
	Fields fields;
	String model;
	Integer pk;

}
