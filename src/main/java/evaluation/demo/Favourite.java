package evaluation.demo;

import java.util.Iterator;
import java.util.List;

public class Favourite {
	private List<Film> favourites;

	public List<Film> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Film> favourites) {
		this.favourites = favourites;
	}
	
	public Film getFilmFromFavourite(String id) throws FilmDoesNotExistException {
		for (Film film : favourites) {
			if (film.getId() == id) return film;
		}
		throw new FilmDoesNotExistException();
	}

	public void add(Film film) {
		favourites.add(film);
	}
	public void delete(String id) throws FilmDoesNotExistException {
		boolean removed = favourites.removeIf(p -> (p.getId() == id));
		
		if (!removed) throw new FilmDoesNotExistException();
	}
	
}
