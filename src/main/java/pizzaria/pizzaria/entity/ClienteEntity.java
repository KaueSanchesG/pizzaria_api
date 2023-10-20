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

    @OneToMany(mappedBy = "cliente")
    private List<EnderecoEntity> enderecoList;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoEntity> pedidoList;
}

