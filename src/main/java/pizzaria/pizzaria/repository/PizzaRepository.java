package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pizzaria.pizzaria.entity.PizzaEntity;

import java.util.List;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    @Query("SELECT p FROM PizzaEntity p JOIN p.saborList s WHERE s.nome = :saborNome")
    List<PizzaEntity> findBySabor(@Param("saborNome") String saborNome);

    List<PizzaEntity> findByTamanho(String tamanho);

    PizzaEntity findByNome(String nome);
}
