package Hassan.Ouadouch.keynoteservice.web;

import Hassan.Ouadouch.keynoteservice.DTO.KeynoteDTO;
import Hassan.Ouadouch.keynoteservice.Mapper.KeynoteMapper;
import Hassan.Ouadouch.keynoteservice.entities.Keynote;
import Hassan.Ouadouch.keynoteservice.service.KeynoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/keynotes")
public class KeynoteController {
    @Autowired
    private KeynoteService keynoteService;

    @Autowired
    private KeynoteMapper keynoteMapper;

    @GetMapping("/")
    public List<KeynoteDTO> getAllKeynotes() {
        List<Keynote> keynotes = keynoteService.getAllKeynotes();
        return keynotes.stream()
                .map(keynoteMapper::keynoteToKeynoteDTO)
                .collect(Collectors.toList());
    }

}
