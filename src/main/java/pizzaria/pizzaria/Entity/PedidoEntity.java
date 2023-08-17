package pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Pedido")
public class PedidoEntity extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "Cliente_id")
    private ClienteEntity cliente;

    @ManyToMany
    @JoinTable(
            name = "Pedido_Produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoEntity> produtos = new ArrayList<>();

    @Column(name = "Entrega")
    private Boolean entrega;

    @Column(name = "Valor_Total")
    private Double valorTotal;

    @Column(name = "Data_Hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "Funcionario_id")
    private FuncionarioEntity funcionario;
}
