package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Funcionario")
public class FuncionarioEntity extends AbstractEntity {
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "funcionario")
    private List<PedidoEntity> pedidoList;
}
