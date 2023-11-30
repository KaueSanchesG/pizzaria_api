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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity credentials;

    @OneToMany(mappedBy = "funcionario")
    private List<PedidoEntity> pedidoList;
}
