package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Funcionario", schema = "pizzaria")
public class FuncionarioEntity extends AbstractEntity {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "funcionario")
    private List<PedidoEntity> pedidoList;
}
