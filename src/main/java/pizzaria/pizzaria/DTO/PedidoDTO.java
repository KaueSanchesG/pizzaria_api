package pizzaria.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private ClienteDTO cliente;
    private List<ProdutoDTO> produtos;
    private Boolean entrega;
    private Double valorTotal;
    private LocalDateTime dataHora;
    private FuncionarioDTO funcionario;
}
