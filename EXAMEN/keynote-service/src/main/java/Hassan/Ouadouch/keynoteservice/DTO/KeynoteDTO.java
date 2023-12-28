package Hassan.Ouadouch.keynoteservice.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor  @AllArgsConstructor @Builder
public class KeynoteDTO {
    private Long id;
    private String name;
    private String email;
}
