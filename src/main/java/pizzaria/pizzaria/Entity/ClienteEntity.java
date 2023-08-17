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

    @Column(name = "CPF", nullable = false, unique = true)
    private int cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> enderecos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoEntity> pedidos;
}

