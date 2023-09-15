package pizzaria.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDTO extends AbstractDTO{
    private ClienteDTO cliente;
    private List<ProdutoDTO> produtos;
    private Boolean entrega;
    private Double valorTotal;
    private LocalDateTime dataHora;
    private FuncionarioDTO funcionario;
}
