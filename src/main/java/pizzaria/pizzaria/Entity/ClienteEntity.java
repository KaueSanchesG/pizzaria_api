package pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Cliente")
public class ClienteEntity extends AbstractEntity {

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "CPF")
    private int cpf;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Endereco_id")
    private EnderecoEntity enderecos;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "Cliente_id")
    private List<PedidoEntity> pedidos;
}

