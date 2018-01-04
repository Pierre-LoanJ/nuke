package evaluation.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import evaluation.demo.Favourite;

@RestController
public class Controller {

    @RequestMapping("/movies")
    public String getMovies() {
    	String url = "https://ghibliapi.herokuapp.com/films";
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Film>> rateResponse = restTemplate.exchange(
        		url,HttpMethod.GET, null, new ParameterizedTypeReference<List<Film>>() {});
        List<Film> films = rateResponse.getBody();
		return films.toString();
    }
    @RequestMapping(value="/movies/{id}", method = RequestMethod.GET)
    public String getMovie(@PathVariable("id") String id) {
    	String url = "https://ghibliapi.herokuapp.com/films/" + id;
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Film> rateResponse = restTemplate.exchange(
        		url,HttpMethod.GET, null, new ParameterizedTypeReference<Film>() {});
        Film film = rateResponse.getBody();
		return film.toString();
    }
    
    @RequestMapping(value = "/favourite", method = RequestMethod.POST)
    public String addFavourite(@RequestBody String body) {
    	String id = "id";
		Favourite.add(id);
    	return "add favourite";
    }
    @RequestMapping(value = "/favourite", method = RequestMethod.GET)
    public String listFavourites() {
		return "list of favourites";
    }
    @RequestMapping(value = "/favourite/{id}", method = RequestMethod.GET)
    public String getFavourite(@PathVariable("id") String id) {
    	Film film = getFilmFromFavourite(id);
    	return "get a favourite";
    }
    @RequestMapping(value = "/favourite/{id}", method = RequestMethod.DELETE)
    public String deleteFavourite(@PathVariable("id") String id) {
		return "delete a favourite";
    }

}