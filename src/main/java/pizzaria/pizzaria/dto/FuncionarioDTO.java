package pizzaria.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
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
public class FuncionarioDTO extends AbstractDTO{
    @NotBlank(message = "O campo não pode ser nullo")
    @Size(min = 3, max = 50, message = "Nome do cliente deve ter entre 3 e 50 caracteres")
    private String nome;
    @NotBlank(message = "Deve conter um login")
    private String login;
    @NotBlank(message = "Deve conter uma senha")
    private String senha;
    @JsonIgnoreProperties("funcionario")
    private List<PedidoDTO> pedidoList;
}
