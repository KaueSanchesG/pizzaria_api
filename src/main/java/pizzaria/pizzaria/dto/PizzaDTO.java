package pizzaria.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pizzaria.pizzaria.entity.enums.Tamanho;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO extends AbstractDTO {
    @NotBlank(message = "Deve conter um nome")
    private String nome;
    @Positive(message = "o valor deve ser maior que 0")
    private Double valor;
    private Tamanho tamanho;
    @NotEmpty(message = "Deve conter um sabor")
    private List<SaborDTO> saborList;
}
