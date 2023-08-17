package pizzaria.pizzaria.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Funcionario")
public class FuncionarioEntity extends AbstractEntity {
    @Column(name = "nome", nullable = false)
    private String nome;
}
