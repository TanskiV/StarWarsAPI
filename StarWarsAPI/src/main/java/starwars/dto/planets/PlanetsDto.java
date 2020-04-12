package starwars.dto.planets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetsDto {
	PlanetsFieldDto fields;
    String model;
    int pk;
}
