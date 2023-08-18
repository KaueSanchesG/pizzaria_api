package pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Endereco")
public class EnderecoEntity extends AbstractEntity {
    @Column(name = "Rua", nullable = false)
    private String rua;

    @Column(name = "Numero", nullable = false)
    private int numero;
}
