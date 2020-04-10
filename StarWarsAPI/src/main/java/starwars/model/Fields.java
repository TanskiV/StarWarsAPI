package starwars.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Fields {
	List<Integer> starships;
	String edited;
	List<Integer> vehicles;
	List<Integer> planets;
	String producer;
	String title;
	String created;
	Integer episode_id;
	String director;
	String release_date;
	String opening_crawl;
	List<Integer> characters;
	List<Integer> species;
}
