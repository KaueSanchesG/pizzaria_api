package pizzaria.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "Sabor")
public class SaborEntity extends AbstractEntity {
    @Column(name = "Nome", nullable = false, unique = true)
    private String nome;
}
