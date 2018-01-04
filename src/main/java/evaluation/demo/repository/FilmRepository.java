package evaluation.demo.repository;

import evaluation.demo.model.FilmDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmDB, String> {

	//public FilmDB findOne(String id);

}
