package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Cliente", schema = "pizzaria")
public class ClienteEntity extends AbstractEntity {
    @Column(nullable = false, length = 50)
    private String nome;

    @Column(unique = true, nullable = false, length = 15)
    private String cpf;

    @ManyToMany
    @JoinTable(
            name = "cliente-endereco",
            schema = "pizzaria",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private List<EnderecoEntity> enderecoList;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoEntity> pedidoList;
}

