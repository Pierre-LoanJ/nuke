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
    	Film film = getFilm(id);
		return film.toString();
    }
    @RequestMapping(value = "/favourite", method = RequestMethod.POST)
    public String addFavourite(@RequestBody String body) {
    	String id = body;
    	// first, we need to get all the film details on the public API
    	Film film = getFilm(id);
    	try {
    		Favourite.addFavourite(film);
    		return "added favourite successfully";
    	}
    	catch (Exception e) {
    		// log the error here or throw it
    		return "failed to insert in data base";
    	}
    }
    @RequestMapping(value = "/favourite", method = RequestMethod.GET)
    public String listFavourites() {
		String results = Favourite.getFavourites().toString();
		return results;
    }
    @RequestMapping(value = "/favourite/{id}", method = RequestMethod.GET)
    public String getFavourite(@PathVariable("id") String id) throws FilmDoesNotExistException {
    	Film film = Favourite.getFavourite(id);
    	return film.toString();
    }
    @RequestMapping(value = "/favourite/{id}", method = RequestMethod.DELETE)
    public String deleteFavourite(@PathVariable("id") String id) throws FilmDoesNotExistException {
		return Favourite.delete(id);
		
    }
    
    private Film getFilm(String id) {
    	String url = "https://ghibliapi.herokuapp.com/films/" + id;
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Film> rateResponse = restTemplate.exchange(
        		url,HttpMethod.GET, null, new ParameterizedTypeReference<Film>() {});
        Film film = rateResponse.getBody();
        return film;
    }

}