package starwars.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"pk"})
@Document(collection = "films")
@Builder
public class Film {
	Fields fields;
	String model;
	@Id
	Integer pk;
}
