package evaluation.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Film")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class FilmDB implements Serializable {
	@Id
    private String id;

    private String title;
    
    private String director;

    private String producer;
    
    private String release_date;

    private String rt_score;

    public FilmDB() {
    	
    }
	public FilmDB(String id, String title, String director, String producer, String release_date, String rt_score) {
		this.id = id;
		this.title = title;
		this.director = director;
		this.producer = producer;
		this.release_date = release_date;
		this.rt_score = rt_score;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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
}
