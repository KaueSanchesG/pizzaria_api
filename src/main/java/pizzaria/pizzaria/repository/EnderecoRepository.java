package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pizzaria.pizzaria.entity.EnderecoEntity;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    @Query("SELECT e FROM EnderecoEntity e WHERE e.cliente.nome = :nome")
    List<EnderecoEntity> findByClienteNome(@Param("nome") String nome);
}
