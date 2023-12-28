package Hassan.Ouadouch.keynoteservice.DAO;

import Hassan.Ouadouch.keynoteservice.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface KeynoteRepository  extends JpaRepository<Keynote,Long> {

}

