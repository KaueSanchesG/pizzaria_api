package pizzaria.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cliente")
public class ClienteEntity extends AbstractEntity {

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 15)
    private String cpf;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private List<EnderecoEntity> enderecos;


}

