package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pizzaria.pizzaria.entity.PizzaEntity;
import pizzaria.pizzaria.entity.enums.Tamanho;

import java.util.List;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    @Query("SELECT p FROM PizzaEntity p JOIN p.saborList s WHERE s.nome = :nome")
    List<PizzaEntity> findBySabor(@Param("nome") String nome);

    @Query("SELECT p FROM PizzaEntity p WHERE p.tamanho = :tamanho")
    List<PizzaEntity> findByTamanho(@Param("tamanho") Tamanho tamanho);


    @Query("SELECT p FROM PizzaEntity p WHERE p.nome =:nome")
    List<PizzaEntity> findByNome(@Param("nome") String nome);
}
