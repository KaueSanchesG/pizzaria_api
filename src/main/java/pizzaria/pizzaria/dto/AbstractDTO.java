package pizzaria.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AbstractDTO {
    private Long id;
    private LocalDateTime cadastro;
    private LocalDateTime atualizacao;
    private boolean ativo;
}
