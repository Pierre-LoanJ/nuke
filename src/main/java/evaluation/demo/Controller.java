package evaluation.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import evaluation.demo.Favourite;
import evaluation.demo.model.FilmDB;
import evaluation.demo.cache.FilmCache;
import evaluation.demo.repository.FilmRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class Controller {
	
	@Autowired
	FilmRepository filmRepo;
	
	 // les APIs accédant à la  base de données
	
	@GetMapping("/favourite")
	public List<FilmDB> getListFavourites() {
		 return filmRepo.findAll();
	}
	@GetMapping("/favourite/{id}")
	public FilmDB getFavourite(@Valid @PathVariable("id") String id) {
		return filmRepo.findOne(id);
	}
	@PostMapping("/favourite")
	public void addFavourite(@Valid @RequestBody String id) {
		FilmCache film = getFilm(id);
		addFavouriteDB(film);
	}
	@DeleteMapping("/favourite/{id}")
	public void deleteFavourite(@Valid @PathVariable("id") String id) {
		FilmCache film = getFilm(id);
		FilmDB filmDB = new FilmDB(film.getId(), film.getTitle(), film.getDirector(), film.getProducer(), film.getRelease_date(), film.getRt_score());
		filmRepo.delete(filmDB);
	}
	private void addFavouriteDB(FilmCache film) {
		FilmDB filmDB = new FilmDB(film.getId(), film.getTitle(), film.getDirector(), film.getProducer(), film.getRelease_date(), film.getRt_score());
		filmRepo.save(filmDB);
	}
	
	// API proxy vers l'API publique de ghibliapi
	
    @RequestMapping("/movies")
    public String getMovies() {
    	List<FilmCache> films = getFilms();
    	return films.toString();
    }
    @RequestMapping(value="/movies/{id}", method = RequestMethod.GET)
    public String getMovie(@PathVariable("id") String id) {
    	FilmCache film = getFilm(id);
		return film.toString();
    }
    
    // les APIs ci dessous fonctionnent en mémoire cache uniquement (pas de base de donnée)
    
    @RequestMapping(value = "/favouriteCache", method = RequestMethod.GET)
    public String getListFavouritesCache() {
		String results = Favourite.getFavourites().toString();
		return results;
    }
    @RequestMapping(value = "/favouriteCache/{id}", method = RequestMethod.GET)
    public String getFavouriteCache(@PathVariable("id") String id) throws FilmDoesNotExistException {
    	FilmCache film = Favourite.getFavourite(id);
    	return film.toString();
    }
    @RequestMapping(value = "/favouriteCache", method = RequestMethod.POST)
    public String addFavouriteCache(@RequestBody String body) {
    	String id = body;
    	// first, we need to get all the film details on the public API
    	FilmCache film = getFilm(id);
    	try {
    		Favourite.addFavourite(film);
    		return "added favourite successfully";
    	}
    	catch (Exception e) {
    		// log the error here or throw it
    		return "failed to insert in data base";
    	}
    }
    @RequestMapping(value = "/favouriteCache/{id}", method = RequestMethod.DELETE)
    public String deleteFavouriteCache(@PathVariable("id") String id) throws FilmDoesNotExistException {
		return Favourite.delete(id);
    }
    
    private FilmCache getFilm(String id) {
    	String url = "https://ghibliapi.herokuapp.com/films/" + id;
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FilmCache> rateResponse = restTemplate.exchange(
        		url,HttpMethod.GET, null, new ParameterizedTypeReference<FilmCache>() {});
        FilmCache film = rateResponse.getBody();
        return film;
    }
    private List<FilmCache> getFilms() {
    	String url = "https://ghibliapi.herokuapp.com/films";
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FilmCache>> rateResponse = restTemplate.exchange(
        		url,HttpMethod.GET, null, new ParameterizedTypeReference<List<FilmCache>>() {});
        List<FilmCache> films = rateResponse.getBody();
		return films;
    }

}