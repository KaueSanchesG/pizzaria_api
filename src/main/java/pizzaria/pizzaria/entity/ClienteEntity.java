package pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Cliente", schema = "pizzaria")
public class ClienteEntity extends AbstractEntity implements UserDetails, Serializable {
    @Column(nullable = false, length = 50)
    private String nome;

    @Column(unique = true, nullable = false, length = 15)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String role;

    @ManyToMany
    @JoinTable(
            name = "cliente-endereco",
            schema = "pizzaria",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private transient List<EnderecoEntity> enderecoList;

    @OneToMany(mappedBy = "cliente")
    private transient List<PedidoEntity> pedidoList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

