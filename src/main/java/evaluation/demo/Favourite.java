package evaluation.demo;

import java.util.ArrayList;
import java.util.List;

import evaluation.demo.cache.FilmCache;

public class Favourite {
	private static List<FilmCache> favourites = new ArrayList<FilmCache>();		// mock of the DB

	public static List<FilmCache> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<FilmCache> favourites) {
		this.favourites = favourites;
	}
	
	public static FilmCache getFavourite(String id) throws FilmDoesNotExistException {
		for (FilmCache film : favourites) {
			if (film.getId().equals(id)) {
				return film;
			}
		}
		throw new FilmDoesNotExistException();
	}

	public static void addFavourite(FilmCache film) {
		favourites.add(film);
	}
	public static String delete(String id) throws FilmDoesNotExistException {
		boolean removed = favourites.removeIf(p -> (p.getId().equals(id)));
		if (!removed) return "not found";//throw new FilmDoesNotExistException();
		else return "ok deletion";
	}
	
}
