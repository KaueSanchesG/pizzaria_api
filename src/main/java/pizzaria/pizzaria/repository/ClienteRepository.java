package pizzaria.pizzaria.repository;

import pizzaria.pizzaria.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    ClienteEntity findByCpf(String cpf);

    List<ClienteEntity> findByNome(String nome);
}
