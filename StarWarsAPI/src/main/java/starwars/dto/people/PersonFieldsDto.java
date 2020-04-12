package starwars.dto.people;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class PersonFieldsDto {
	String edited;
	String name;
	String created;
	String gender;
	String skin_color;
	String hair_color;
	String height;
	String eye_color;
	String mass;
	int homeworld;
	String birth_year;
	String image;
}
