package pizzaria.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO extends AbstractDTO{
    private String nome;
    private Double valor;
}
