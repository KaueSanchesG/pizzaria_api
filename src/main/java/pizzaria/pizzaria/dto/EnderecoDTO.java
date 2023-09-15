package pizzaria.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO extends AbstractDTO{
    private String rua;
    private int numero;
}
