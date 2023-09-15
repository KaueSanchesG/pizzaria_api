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
    @Column(name = "Nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "Valor", nullable = false)
    private Double valor;

    @ManyToMany
    @JoinTable(
            name = "produto_pizzasabor",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "pizzasabor_id")
    )
    private List<SaborEntity> pizzaSabor;

}
