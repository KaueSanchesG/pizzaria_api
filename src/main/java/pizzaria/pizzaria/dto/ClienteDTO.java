package pizzaria.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends AbstractDTO{
    @NotBlank(message = "Campo não pode ser nullo")
    @Size(min = 3, max = 50, message = "Nome do cliente deve ter entre 3 e 50 caracteres")
    private String nome;
    @CPF(message = "o CPF deve seguir a norma padrão")
    private String cpf;
    @JsonIgnoreProperties("cliente")
    private List<EnderecoDTO> enderecos;
    @JsonIgnoreProperties("cliente")
    private List<PedidoDTO> pedidoList;
}
