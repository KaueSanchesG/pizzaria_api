package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Endereco")
public class EnderecoEntity extends AbstractEntity {
    @Column(name = "Rua", nullable = false, length = 50)
    private String rua;

    @Column(name = "Numero", nullable = false)
    private int numero;
}
