package pizzaria.pizzaria.Repository;

import pizzaria.pizzaria.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    ClienteEntity findByCpf(String cpf);
}
