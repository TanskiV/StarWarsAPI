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
public class PlanetsFieldDto {
	String edited;
    String climate;
    String surface_water;
    String name;
    String diameter;
    String rotation_period;
    String created;
    String terrain;
    String gravity;
    String orbital_period;
    String population;
}
