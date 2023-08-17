package pizzaria.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private ClienteDTO cliente;
    private List<ProdutoDTO> produtos;
    private Boolean entrega;
    private Double valorTotal;
    private LocalDateTime dataHora;
    private FuncionarioDTO funcionario;
}
