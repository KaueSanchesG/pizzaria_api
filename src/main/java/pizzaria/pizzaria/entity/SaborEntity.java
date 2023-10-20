package pizzaria.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Sabor", schema = "pizzaria")
public class SaborEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "saborList")
    private List<PizzaEntity> pizzaList;
}
