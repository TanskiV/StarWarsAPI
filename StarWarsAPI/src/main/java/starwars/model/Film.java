package starwars.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starwars.dto.film.Fields;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"pk"})
@Document(collection = "films")
public class Film {
	Fields fields;
	String model;
	@Id
	Integer pk;
}
