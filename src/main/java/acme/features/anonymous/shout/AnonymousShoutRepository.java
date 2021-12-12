package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.entidades1.Entidad1;
import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository{
	
	@Query("select s from Shout s")
	Collection<Shout> findMany();

	@Query("select s from Shout s where s.id = ?1")
	Shout findOneShoutById(int id);
	
	@Query("select c from Configuration c")
	List<Configuration> findManySpamWord();
	
	@Query("select s.entidad1 from Shout s")
	Collection<Entidad1> findManyEntidad1();
	

}
