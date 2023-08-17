package pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Endereco")
public class EnderecoEntity extends AbstractEntity {
    @Column(name = "Rua")
    private String rua;

    @Column(name = "Numero")
    private int numero;

    @ManyToOne
    @JoinColumn(name = "Cliente_id")
    private ClienteEntity cliente;
}
