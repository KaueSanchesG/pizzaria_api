package pizzaria.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PedidoEntity> pedidos;
}
