package Hassan.Ouadouch.keynoteservice.Mapper;

import Hassan.Ouadouch.keynoteservice.DTO.KeynoteDTO;
import Hassan.Ouadouch.keynoteservice.entities.Keynote;
import org.springframework.stereotype.Component;

@Component
public class KeynoteMapper {
    public KeynoteDTO keynoteToKeynoteDTO(Keynote keynote) {
        KeynoteDTO keynoteDTO = new KeynoteDTO();
        keynoteDTO.setId(keynote.getId());
        keynoteDTO.setName(keynote.getName());
        keynoteDTO.setEmail(keynote.getEmail());
        return keynoteDTO;
    }

    public Keynote keynoteDTOToKeynote(KeynoteDTO keynoteDTO) {
        Keynote keynote = new Keynote();
        keynote.setId(keynoteDTO.getId());
        keynote.setName(keynoteDTO.getName());
        keynote.setEmail(keynoteDTO.getEmail());
        return keynote;
    }
}
