package pizzaria.pizzaria.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO extends AbstractDTO{
    @NotNull(message = "Deve conter um cliente")
    private ClienteDTO cliente;
    @NotNull(message = "Deve conter pelo menos um produto")
    private List<ProdutoDTO> produtos;
    private Boolean entrega;
    private Double valorTotal;
    private LocalDateTime dataHora;
    @NotNull(message = "Deve conter um funcionario")
    private FuncionarioDTO funcionario;
}
