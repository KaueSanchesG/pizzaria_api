package pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Cliente")
public class ClienteEntity extends AbstractEntity {

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "CPF", length = 15)
    private String cpf;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private List<EnderecoEntity> enderecos;

}

