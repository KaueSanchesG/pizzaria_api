package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Produto", schema = "pizzaria")
public class ProdutoEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Double valor;

    @ManyToMany(mappedBy = "produtoList")
    private List<PedidoEntity> pedidoList;
}
