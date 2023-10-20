package pizzaria.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Produto", schema = "pizzaria")
public class ProdutoEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne
    private PedidoEntity pedido;
}
