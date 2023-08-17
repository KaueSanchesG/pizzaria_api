package pizzaria.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double valor;
}
