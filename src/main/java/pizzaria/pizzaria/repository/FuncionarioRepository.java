package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.pizzaria.entity.FuncionarioEntity;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    FuncionarioEntity findByNome(String nome);
}
