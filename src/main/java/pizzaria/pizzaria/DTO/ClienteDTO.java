package pizzaria.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private List<EnderecoDTO> enderecos;
    private List<PedidoDTO> pedidos;
}
