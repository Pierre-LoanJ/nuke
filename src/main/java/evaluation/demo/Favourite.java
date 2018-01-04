package evaluation.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Favourite {
	private static List<Film> favourites = new ArrayList<Film>();		// mock of the DB

	public static List<Film> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Film> favourites) {
		this.favourites = favourites;
	}
	
	public static Film getFavourite(String id) throws FilmDoesNotExistException {
		for (Film film : favourites) {
			if (film.getId().equals(id)) {
				return film;
			}
		}
		throw new FilmDoesNotExistException();
	}

	public static void addFavourite(Film film) {
		favourites.add(film);
	}
	public static String delete(String id) throws FilmDoesNotExistException {
		boolean removed = favourites.removeIf(p -> (p.getId().equals(id)));
		if (!removed) return "not found";//throw new FilmDoesNotExistException();
		else return "ok deletion";
	}
	
}
