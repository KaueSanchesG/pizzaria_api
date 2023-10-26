package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pizzaria.pizzaria.entity.enums.Tamanho;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Pizza", schema = "pizzaria")
public class PizzaEntity extends AbstractEntity {
    @Column(nullable = false)
    private String nome;

    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tamanho tamanho;

    @ManyToMany
    @JoinTable(
            name = "pizza_sabor",
            schema = "pizzaria",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "sabor_id")
    )
    private List<SaborEntity> saborList;

    @ManyToMany(mappedBy = "pizzaList")
    private List<PedidoEntity> pedidoList;
}

