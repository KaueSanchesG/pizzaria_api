package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Produto")
public class ProdutoEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Double valor;

    @ManyToMany(mappedBy = "produtos")
    private List<PedidoEntity> pedidos;

    @ManyToMany
    @JoinTable(
            name = "produto_pizzasabor",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "pizzasabor_id")
    )
    private List<SaborEntity> pizzaSabor;

}
