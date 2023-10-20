package pizzaria.pizzaria.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO extends AbstractDTO{
    private String nome;
    @Positive(message = "O numero deve ser positivo")
    private Double valor;
}
