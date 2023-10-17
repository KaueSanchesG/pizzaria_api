package pizzaria.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO extends AbstractDTO{
    @NotBlank(message = "O campo n pode ser nullo")
    @Size(min = 10, max = 50, message = "A rua deve conter entre 10-50 caracteres")
    private String rua;
    @NotNull(message = "O campo n pode ser nullo")
    @Positive(message = "O numero deve ser positivo")
    private int numero;
    @JsonIgnoreProperties({"pedidoList", "enderecos"})
    @NotNull(message = "O campo n pode ser nullo")
    private ClienteDTO cliente;
}
