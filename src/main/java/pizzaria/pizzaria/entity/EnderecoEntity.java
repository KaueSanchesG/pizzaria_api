package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Endereco", schema = "pizzaria")
public class EnderecoEntity extends AbstractEntity {
    @Column(nullable = false, length = 50)
    private String rua;

    @Column(nullable = false)
    private int numero;

    @ManyToMany(mappedBy = "enderecoList")
    private List<ClienteEntity> clienteList;
}
