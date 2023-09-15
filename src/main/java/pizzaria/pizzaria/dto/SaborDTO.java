package pizzaria.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaborDTO extends AbstractDTO{
    @NotBlank(message = "O campo não pode ser nullo")
    @Size(min = 3, max = 50, message = "Nome do sabor deve ter entre 3 e 50 caracteres")
    private String nome;
}
