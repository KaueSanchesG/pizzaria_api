package pizzaria.pizzaria.repository;

import pizzaria.pizzaria.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    ClienteEntity findByCpf(String cpf);

    ClienteEntity findByNome(String nome);
}
