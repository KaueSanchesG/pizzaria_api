package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pizzaria.pizzaria.entity.FuncionarioEntity;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    @Query("SELECT f FROM FuncionarioEntity f WHERE f.login =:login")
    FuncionarioEntity findByLogin(@Param("login") String login);

    List<FuncionarioEntity> findByNome(String nome);
}
