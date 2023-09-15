package pizzaria.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO extends AbstractDTO{
    @NotBlank(message = "O campo não pode ser nullo")
    @Size(min = 3, max = 50, message = "Nome do produto deve ter entre 3 e 50 caracteres")
    private String nome;
    @NotNull(message = "O campo nã pode ser nullo")
    private Double valor;
    private List<PedidoDTO> pedidos;
    private List<SaborDTO> sabores;
}
