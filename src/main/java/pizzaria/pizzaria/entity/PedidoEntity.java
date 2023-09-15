package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Pedido")
public class PedidoEntity extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoEntity> produtos;

    @Column
    private Boolean entrega;

    @Column
    private Double valorTotal;

    @Column
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionarioEntity funcionario;
}
