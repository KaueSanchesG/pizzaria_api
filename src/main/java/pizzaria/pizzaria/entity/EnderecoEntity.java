package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Endereco", schema = "pizzaria")
public class EnderecoEntity extends AbstractEntity {
    @Column(nullable = false, length = 50)
    private String rua;

    @Column(nullable = false)
    private int numero;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}
