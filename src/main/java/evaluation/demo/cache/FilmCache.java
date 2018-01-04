package evaluation.demo.cache;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmCache {

    private String id;
    private String title;
    //private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    
    public FilmCache() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
/*
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
*/
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getRt_score() {
		return rt_score;
	}

	public void setRt_score(String rt_score) {
		this.rt_score = rt_score;
	}
	
	@Override
    public String toString() {
        return "{" + " \n" +
                " \t" +
        		"id=" + id + ", \n" +
                "title='" + title + '\'' + " , \n" +
                "director'=" + director + '\'' + " , \n" +
                "producer'=" + producer + '\'' + " , \n" +
                "release_date'=" + release_date + '\'' +  " , \n" +
                "rt_score'=" + rt_score + '\'' + " \n" +
                '}';
    }
}
