package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.pizzaria.entity.FuncionarioEntity;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    FuncionarioEntity findByLogin(String login);

    List<FuncionarioEntity> findByNome(String nome);
}
