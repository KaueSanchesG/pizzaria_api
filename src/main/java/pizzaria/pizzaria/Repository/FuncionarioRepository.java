package pizzaria.pizzaria.Repository;

import pizzaria.pizzaria.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.pizzaria.Entity.FuncionarioEntity;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
}
