package Hassan.Ouadouch.keynoteservice.service;

import Hassan.Ouadouch.keynoteservice.DAO.KeynoteRepository;
import Hassan.Ouadouch.keynoteservice.entities.Keynote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeynoteService {
    @Autowired
    private KeynoteRepository keynoteRepository;

    public List<Keynote> getAllKeynotes() {
        return keynoteRepository.findAll();
    }

}
